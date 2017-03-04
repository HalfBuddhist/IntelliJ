#!/usr/bin/env python
# coding=utf-8

from random import randint

N, M = [int(x) for x in raw_input().split()]
Cmin, Cmax = [int(x) for x in raw_input().split()]

assert (0 <= M <= (N * (N - 1)) // 2)
assert (Cmin <= Cmax)

C = [randint(Cmin, Cmax) for _ in range(N)]
E = set()

print("%d %s" % (N, M))
print(" ".join(map(str, C)))

while len(E) < M:
    u = randint(1, N - 1)
    v = randint(u + 1, N)
    E.add((u, v))

for e in sorted(E):
    print("%d %d" % e)
