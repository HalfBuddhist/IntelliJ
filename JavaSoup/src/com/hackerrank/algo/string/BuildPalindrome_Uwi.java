package com.hackerrank.algo.string;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class BuildPalindrome_Uwi {
    InputStream is;
    PrintWriter out;
    String INPUT = "";

    void solve() {
        for (int T = ni(); T > 0; T--) {
            char[] s = ns().toCharArray();
            char[] t = ns().toCharArray();

            String b1 = go(s, t);
//			tr(best);
            char[] rs = rev(s);
            char[] rt = rev(t);
            String b2 = go(rt, rs);

            if (b1 == null && b2 == null) {
                out.println(-1);
            } else if (b1 == null) {
                out.println(b2);
            } else if (b2 == null) {
                out.println(b1);
            } else if (b1.length() > b2.length() || b1.length() == b2.length() && b1.compareTo(b2) < 0) {
                out.println(b1);
            } else {
                out.println(b2);
            }
        }
    }

    public static char[] rev(char[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            char c = a[i];
            a[i] = a[j];
            a[j] = c;
        }
        return a;
    }

    String go(char[] s, char[] t) {
        char[] st = new char[s.length + t.length];
        for (int i = 0; i < s.length; i++) st[i] = s[s.length - 1 - i];
        for (int i = 0; i < t.length; i++) st[i + s.length] = t[i];

//		String S = new String(s);
//		String ST = new String(st);
        int[] sa = sa(st);
        int nn = s.length + t.length;
        int[] isa = new int[nn];
        for (int i = 0; i < nn; i++) isa[sa[i]] = i;
        int[] lcp = buildLCP(st, sa);
//		tr(st);
//		tr(sa);

        int[] llcp = new int[nn];
        {
            int cur = 0;
            for (int i = 0; i < nn; i++) {
                if (sa[i] < s.length) {
                    llcp[s.length - 1 - sa[i]] = cur;
                } else {
                    cur = 9999999;
                }
                if (i + 1 < nn) cur = Math.min(cur, lcp[i + 1]);
            }
        }
        int[] rlcp = new int[nn];
        {
            int cur = 0;
            for (int i = nn - 1; i >= 0; i--) {
                if (sa[i] < s.length) {
                    rlcp[s.length - 1 - sa[i]] = cur;
                } else {
                    cur = 9999999;
                }
                cur = Math.min(cur, lcp[i]);
            }
        }
//		tr(llcp, rlcp);
        int[] rad = palindrome(s);
//		tr("s", new String(s));
//		tr(rad);

        int[] pals = new int[s.length];

        {
            int[][] es = new int[s.length * 2 - 1][];
            for (int i = 0; i < s.length * 2 - 1; i++) {
                es[i] = new int[]{(i - rad[i] + 1) / 2, rad[i]};
            }
            Arrays.sort(es, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
            int p = 0;
            int cur = 0;
            for (int i = 0; i < s.length; i++) {
                while (p < es.length && es[p][0] <= i) {
                    cur = Math.max(cur, es[p][1]);
                    p++;
                }
                pals[i] = Math.max(pals[i], cur);
                cur = Math.max(cur - 2, 0);
            }
        }
//		tr("pals", pals);

        char[] srs = new char[s.length * 2];
        for (int i = 0; i < s.length; i++) {
            srs[i] = srs[s.length * 2 - 1 - i] = s[i];
        }
        int[] ssa = sa(srs);
        int[] issa = new int[srs.length];
        for (int i = 0; i < srs.length; i++) issa[ssa[i]] = i;
        int[] slcp = buildLCP(srs, ssa);
        SegmentTreeRMQ sts = new SegmentTreeRMQ(slcp);

        int maxlen = 0;
        int[] lbest = null;
        for (int start = 0; start < s.length; start++) {
            int xlcp = Math.min(start + 1, Math.max(llcp[start], rlcp[start]));
            if (xlcp == 0) continue;
            int lpal = start + 1 < s.length ? pals[start + 1] : 0;
//			tr(start, xlcp, lpal);
            int len = lpal + xlcp * 2;
            if (len > maxlen) {
                maxlen = len;
                lbest = new int[]{start - xlcp + 1, start + lpal + 1, s.length - 1 - start + s.length, s.length - 1 - (start - xlcp + 1) + 1 + s.length};
            } else if (len == maxlen) {
                int[] can = new int[]{start - xlcp + 1, start + lpal + 1, s.length - 1 - start + s.length, s.length - 1 - (start - xlcp + 1) + 1 + s.length};
                int ulcp = sts.minx(Math.min(issa[can[0]], issa[lbest[0]]) + 1, Math.max(issa[can[0]], issa[lbest[0]]) + 1);
                if (ulcp < can[1] - can[0] && ulcp < lbest[1] - lbest[0]) {
                    if (issa[can[0]] < issa[lbest[0]]) {
                        lbest = can;
                    }
                    continue;
                }
                int L = can[1] - can[0];
                int R = lbest[1] - lbest[0];
                if (L < R) {
                    int lpos = can[2], rpos = lbest[0] + L;
                    ulcp = sts.minx(Math.min(issa[lpos], issa[rpos]) + 1, Math.max(issa[lpos], issa[rpos]) + 1);
                    if (ulcp < R - L) {
                        if (issa[lpos] < issa[rpos]) {
                            lbest = can;
                        }
                        continue;
                    }

                    lpos = can[2] + R - L;
                    rpos = lbest[2];
                    ulcp = sts.minx(Math.min(issa[lpos], issa[rpos]) + 1, Math.max(issa[lpos], issa[rpos]) + 1);
                    if (issa[lpos] < issa[rpos]) {
                        lbest = can;
                    }
                } else {
                    int lpos = can[0] + R, rpos = lbest[2];
                    ulcp = sts.minx(Math.min(issa[lpos], issa[rpos]) + 1, Math.max(issa[lpos], issa[rpos]) + 1);
                    if (ulcp < L - R) {
                        if (issa[lpos] < issa[rpos]) {
                            lbest = can;
                        }
                        continue;
                    }

                    lpos = can[2];
                    rpos = lbest[2] + L - R;
                    ulcp = sts.minx(Math.min(issa[lpos], issa[rpos]) + 1, Math.max(issa[lpos], issa[rpos]) + 1);
                    if (issa[lpos] < issa[rpos]) {
                        lbest = can;
                    }
                }
            }
        }

        if (lbest == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = lbest[0]; i < lbest[1]; i++) sb.append(srs[i]);
            for (int i = lbest[2]; i < lbest[3]; i++) sb.append(srs[i]);
            return sb.toString();
        }
    }

    public static class SegmentTreeRMQ {
        public int M, H, N;
        public int[] st;

        public SegmentTreeRMQ(int n) {
            N = n;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new int[M];
            Arrays.fill(st, 0, M, Integer.MAX_VALUE);
        }

        public SegmentTreeRMQ(int[] a) {
            N = a.length;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;
            st = new int[M];
            for (int i = 0; i < N; i++) {
                st[H + i] = a[i];
            }
            Arrays.fill(st, H + N, M, Integer.MAX_VALUE);
            for (int i = H - 1; i >= 1; i--) propagate(i);
        }

        public void update(int pos, int x) {
            st[H + pos] = x;
            for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1) propagate(i);
        }

        private void propagate(int i) {
            st[i] = Math.min(st[2 * i], st[2 * i + 1]);
        }

        public int minx(int l, int r) {
            if (l >= r) return 0;
            int min = Integer.MAX_VALUE;
            while (l != 0) {
                int f = l & -l;
                if (l + f > r) break;
                int v = st[(H + l) / f];
                if (v < min) min = v;
                l += f;
            }

            while (l < r) {
                int f = r & -r;
                int v = st[(H + r) / f - 1];
                if (v < min) min = v;
                r -= f;
            }
            return min;
        }

        public int min(int l, int r) {
            return l >= r ? 0 : min(l, r, 0, H, 1);
        }

        private int min(int l, int r, int cl, int cr, int cur) {
            if (l <= cl && cr <= r) {
                return st[cur];
            } else {
                int mid = cl + cr >>> 1;
                int ret = Integer.MAX_VALUE;
                if (cl < r && l < mid) {
                    ret = Math.min(ret, min(l, r, cl, mid, 2 * cur));
                }
                if (mid < r && l < cr) {
                    ret = Math.min(ret, min(l, r, mid, cr, 2 * cur + 1));
                }
                return ret;
            }
        }

        public int firstle(int l, int v) {
            int cur = H + l;
            while (true) {
                if (st[cur] <= v) {
                    if (cur < H) {
                        cur = 2 * cur;
                    } else {
                        return cur - H;
                    }
                } else {
                    cur++;
                    if ((cur & cur - 1) == 0) return -1;
                    if ((cur & 1) == 0) cur >>>= 1;
                }
            }
        }

        public int lastle(int l, int v) {
            int cur = H + l;
            while (true) {
                if (st[cur] <= v) {
                    if (cur < H) {
                        cur = 2 * cur + 1;
                    } else {
                        return cur - H;
                    }
                } else {
                    if ((cur & cur - 1) == 0) return -1;
                    cur--;
                    if ((cur & 1) == 1) cur >>>= 1;
                }
            }
        }
    }


    public static int[] palindrome(char[] str) {
        int n = str.length;
        int[] r = new int[2 * n];
        int k = 0;
        for (int i = 0, j = 0; i < 2 * n; i += k, j = Math.max(j - k, 0)) {
            // normally
            while (i - j >= 0 && i + j + 1 < 2 * n && str[(i - j) / 2] == str[(i + j + 1) / 2]) j++;
            r[i] = j;

            // skip based on the theorem
            for (k = 1; i - k >= 0 && r[i] - k >= 0 && r[i - k] != r[i] - k; k++) {
                r[i + k] = Math.min(r[i - k], r[i] - k);
            }
        }
        return r;
    }


    private static interface BaseArray {
        public int get(int i);

        public void set(int i, int val);

        public int update(int i, int val);
    }

    private static class CharArray implements BaseArray {
        private char[] m_A = null;
        private int m_pos = 0;

        CharArray(char[] A, int pos) {
            m_A = A;
            m_pos = pos;
        }

        public int get(int i) {
            return m_A[m_pos + i] & 0xffff;
        }

        public void set(int i, int val) {
            m_A[m_pos + i] = (char) (val & 0xffff);
        }

        public int update(int i, int val) {
            return m_A[m_pos + i] += val & 0xffff;
        }
    }

    private static class IntArray implements BaseArray {
        private int[] m_A = null;
        private int m_pos = 0;

        IntArray(int[] A, int pos) {
            m_A = A;
            m_pos = pos;
        }

        public int get(int i) {
            return m_A[m_pos + i];
        }

        public void set(int i, int val) {
            m_A[m_pos + i] = val;
        }

        public int update(int i, int val) {
            return m_A[m_pos + i] += val;
        }
    }

    /* find the start or end of each bucket */
    private static void getCounts(BaseArray T, BaseArray C, int n, int k) {
        int i;
        for (i = 0; i < k; ++i) {
            C.set(i, 0);
        }
        for (i = 0; i < n; ++i) {
            C.update(T.get(i), 1);
        }
    }

    private static void getBuckets(BaseArray C, BaseArray B, int k, boolean end) {
        int i, sum = 0;
        if (end != false) {
            for (i = 0; i < k; ++i) {
                sum += C.get(i);
                B.set(i, sum);
            }
        } else {
            for (i = 0; i < k; ++i) {
                sum += C.get(i);
                B.set(i, sum - C.get(i));
            }
        }
    }

    /* sort all type LMS suffixes */
    private static void LMSsort(BaseArray T, int[] SA, BaseArray C,
                                BaseArray B, int n, int k) {
        int b, i, j;
        int c0, c1;
        /* compute SAl */
        if (C == B) {
            getCounts(T, C, n, k);
        }
        getBuckets(C, B, k, false); /* find starts of buckets */
        j = n - 1;
        b = B.get(c1 = T.get(j));
        --j;
        SA[b++] = (T.get(j) < c1) ? ~j : j;
        for (i = 0; i < n; ++i) {
            if (0 < (j = SA[i])) {
                if ((c0 = T.get(j)) != c1) {
                    B.set(c1, b);
                    b = B.get(c1 = c0);
                }
                --j;
                SA[b++] = (T.get(j) < c1) ? ~j : j;
                SA[i] = 0;
            } else if (j < 0) {
                SA[i] = ~j;
            }
        }
        /* compute SAs */
        if (C == B) {
            getCounts(T, C, n, k);
        }
        getBuckets(C, B, k, true); /* find ends of buckets */
        for (i = n - 1, b = B.get(c1 = 0); 0 <= i; --i) {
            if (0 < (j = SA[i])) {
                if ((c0 = T.get(j)) != c1) {
                    B.set(c1, b);
                    b = B.get(c1 = c0);
                }
                --j;
                SA[--b] = (T.get(j) > c1) ? ~(j + 1) : j;
                SA[i] = 0;
            }
        }
    }

    private static int LMSpostproc(BaseArray T, int[] SA, int n, int m) {
        int i, j, p, q, plen, qlen, name;
        int c0, c1;
        boolean diff;

		/*
		 * compact all the sorted substrings into the first m items of SA 2*m
		 * must be not larger than n (proveable)
		 */
        for (i = 0; (p = SA[i]) < 0; ++i) {
            SA[i] = ~p;
        }
        if (i < m) {
            for (j = i, ++i; ; ++i) {
                if ((p = SA[i]) < 0) {
                    SA[j++] = ~p;
                    SA[i] = 0;
                    if (j == m) {
                        break;
                    }
                }
            }
        }

		/* store the length of all substrings */
        i = n - 1;
        j = n - 1;
        c0 = T.get(n - 1);
        do {
            c1 = c0;
        } while ((0 <= --i) && ((c0 = T.get(i)) >= c1));
        for (; 0 <= i; ) {
            do {
                c1 = c0;
            } while ((0 <= --i) && ((c0 = T.get(i)) <= c1));
            if (0 <= i) {
                SA[m + ((i + 1) >> 1)] = j - i;
                j = i + 1;
                do {
                    c1 = c0;
                } while ((0 <= --i) && ((c0 = T.get(i)) >= c1));
            }
        }

		/* find the lexicographic names of all substrings */
        for (i = 0, name = 0, q = n, qlen = 0; i < m; ++i) {
            p = SA[i];
            plen = SA[m + (p >> 1)];
            diff = true;
            if ((plen == qlen) && ((q + plen) < n)) {
                for (j = 0; (j < plen) && (T.get(p + j) == T.get(q + j)); ++j) {
                }
                if (j == plen) {
                    diff = false;
                }
            }
            if (diff != false) {
                ++name;
                q = p;
                qlen = plen;
            }
            SA[m + (p >> 1)] = name;
        }

        return name;
    }

    /* compute SA and BWT */
    private static void induceSA(BaseArray T, int[] SA, BaseArray C,
                                 BaseArray B, int n, int k) {
        int b, i, j;
        int c0, c1;
		/* compute SAl */
        if (C == B) {
            getCounts(T, C, n, k);
        }
        getBuckets(C, B, k, false); /* find starts of buckets */
        j = n - 1;
        b = B.get(c1 = T.get(j));
        SA[b++] = ((0 < j) && (T.get(j - 1) < c1)) ? ~j : j;
        for (i = 0; i < n; ++i) {
            j = SA[i];
            SA[i] = ~j;
            if (0 < j) {
                if ((c0 = T.get(--j)) != c1) {
                    B.set(c1, b);
                    b = B.get(c1 = c0);
                }
                SA[b++] = ((0 < j) && (T.get(j - 1) < c1)) ? ~j : j;
            }
        }
		/* compute SAs */
        if (C == B) {
            getCounts(T, C, n, k);
        }
        getBuckets(C, B, k, true); /* find ends of buckets */
        for (i = n - 1, b = B.get(c1 = 0); 0 <= i; --i) {
            if (0 < (j = SA[i])) {
                if ((c0 = T.get(--j)) != c1) {
                    B.set(c1, b);
                    b = B.get(c1 = c0);
                }
                SA[--b] = ((j == 0) || (T.get(j - 1) > c1)) ? ~j : j;
            } else {
                SA[i] = ~j;
            }
        }
    }

    /*
     * find the suffix array SA of T[0..n-1] in {0..k-1}^n use a working space
     * (excluding T and SA) of at most 2n+O(1) for a constant alphabet
     */
    private static void SA_IS(BaseArray T, int[] SA, int fs, int n, int k) {
        BaseArray C, B, RA;
        int i, j, b, m, p, q, name, newfs;
        int c0, c1;
        int flags = 0;

        if (k <= 256) {
            C = new IntArray(new int[k], 0);
            if (k <= fs) {
                B = new IntArray(SA, n + fs - k);
                flags = 1;
            } else {
                B = new IntArray(new int[k], 0);
                flags = 3;
            }
        } else if (k <= fs) {
            C = new IntArray(SA, n + fs - k);
            if (k <= (fs - k)) {
                B = new IntArray(SA, n + fs - k * 2);
                flags = 0;
            } else if (k <= 1024) {
                B = new IntArray(new int[k], 0);
                flags = 2;
            } else {
                B = C;
                flags = 8;
            }
        } else {
            C = B = new IntArray(new int[k], 0);
            flags = 4 | 8;
        }

		/*
		 * stage 1: reduce the problem by at least 1/2 sort all the
		 * LMS-substrings
		 */
        getCounts(T, C, n, k);
        getBuckets(C, B, k, true); /* find ends of buckets */
        for (i = 0; i < n; ++i) {
            SA[i] = 0;
        }
        b = -1;
        i = n - 1;
        j = n;
        m = 0;
        c0 = T.get(n - 1);
        do {
            c1 = c0;
        } while ((0 <= --i) && ((c0 = T.get(i)) >= c1));
        for (; 0 <= i; ) {
            do {
                c1 = c0;
            } while ((0 <= --i) && ((c0 = T.get(i)) <= c1));
            if (0 <= i) {
                if (0 <= b) {
                    SA[b] = j;
                }
                b = B.update(c1, -1);
                j = i;
                ++m;
                do {
                    c1 = c0;
                } while ((0 <= --i) && ((c0 = T.get(i)) >= c1));
            }
        }
        if (1 < m) {
            LMSsort(T, SA, C, B, n, k);
            name = LMSpostproc(T, SA, n, m);
        } else if (m == 1) {
            SA[b] = j + 1;
            name = 1;
        } else {
            name = 0;
        }

		/*
		 * stage 2: solve the reduced problem recurse if names are not yet
		 * unique
		 */
        if (name < m) {
            if ((flags & 4) != 0) {
                C = null;
                B = null;
            }
            if ((flags & 2) != 0) {
                B = null;
            }
            newfs = (n + fs) - (m * 2);
            if ((flags & (1 | 4 | 8)) == 0) {
                if ((k + name) <= newfs) {
                    newfs -= k;
                } else {
                    flags |= 8;
                }
            }
            for (i = m + (n >> 1) - 1, j = m * 2 + newfs - 1; m <= i; --i) {
                if (SA[i] != 0) {
                    SA[j--] = SA[i] - 1;
                }
            }
            RA = new IntArray(SA, m + newfs);
            SA_IS(RA, SA, newfs, m, name);
            RA = null;

            i = n - 1;
            j = m * 2 - 1;
            c0 = T.get(n - 1);
            do {
                c1 = c0;
            } while ((0 <= --i) && ((c0 = T.get(i)) >= c1));
            for (; 0 <= i; ) {
                do {
                    c1 = c0;
                } while ((0 <= --i) && ((c0 = T.get(i)) <= c1));
                if (0 <= i) {
                    SA[j--] = i + 1;
                    do {
                        c1 = c0;
                    } while ((0 <= --i) && ((c0 = T.get(i)) >= c1));
                }
            }

            for (i = 0; i < m; ++i) {
                SA[i] = SA[m + SA[i]];
            }
            if ((flags & 4) != 0) {
                C = B = new IntArray(new int[k], 0);
            }
            if ((flags & 2) != 0) {
                B = new IntArray(new int[k], 0);
            }
        }

		/* stage 3: induce the result for the original problem */
        if ((flags & 8) != 0) {
            getCounts(T, C, n, k);
        }
		/* put all start-most S characters into their buckets */
        if (1 < m) {
            getBuckets(C, B, k, true); /* find ends of buckets */
            i = m - 1;
            j = n;
            p = SA[m - 1];
            c1 = T.get(p);
            do {
                q = B.get(c0 = c1);
                while (q < j) {
                    SA[--j] = 0;
                }
                do {
                    SA[--j] = p;
                    if (--i < 0) {
                        break;
                    }
                    p = SA[i];
                } while ((c1 = T.get(p)) == c0);
            } while (0 <= i);
            while (0 < j) {
                SA[--j] = 0;
            }
        }
        induceSA(T, SA, C, B, n, k);
        C = null;
        B = null;
    }

    /* char */
    public static void suffixsort(char[] T, int[] SA, int n) {
        if ((T == null) || (SA == null) || (T.length < n) || (SA.length < n)) {
            return;
        }
        if (n <= 1) {
            if (n == 1) {
                SA[0] = 0;
            }
            return;
        }
        SA_IS(new CharArray(T, 0), SA, 0, n, 128);
    }

    public static int[] sa(char[] T) {
        int n = T.length;
        int[] sa = new int[n];
        suffixsort(T, sa, n);
        return sa;
    }

    public static int[] buildLCP(char[] str, int[] sa) {
        int n = str.length;
        int h = 0;
        int[] lcp = new int[n];
        int[] isa = new int[n];
        for (int i = 0; i < n; i++) isa[sa[i]] = i;
        for (int i = 0; i < n; i++) {
            if (isa[i] > 0) {
                for (int j = sa[isa[i] - 1]; j + h < n && i + h < n && str[j + h] == str[i + h]; h++) ;
                lcp[isa[i]] = h;
            } else {
                lcp[isa[i]] = 0;
            }
            if (h > 0) h--;
        }
        return lcp;
    }


    void run() throws Exception {
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        if (!INPUT.isEmpty()) tr(System.currentTimeMillis() - s + "ms");
    }

    public static void main(String[] args) throws Exception {
        new BuildPalindrome_Uwi().run();
    }

    private byte[] inbuf = new byte[1024];
    private int lenbuf = 0, ptrbuf = 0;

    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }

    private char nc() {
        return (char) skip();
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
}