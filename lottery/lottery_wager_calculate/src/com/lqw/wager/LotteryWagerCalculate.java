package com.lqw.wager;


/**
 * 计算双胜的投注额
 */
import java.util.Scanner;

public class LotteryWagerCalculate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input the first pei:");
		float first_pei = scanner.nextFloat();
		System.out.println("Please input the second pei:"); 
		float second_pei = scanner.nextFloat();
		System.out.println("Please input the wager:"); 
		float wager = scanner.nextFloat();


		float first_wager = wager, second_wager = 0, threshold = 0.1f;
		while (!((first_wager * first_pei) > wager && second_pei * second_wager > wager) && first_wager > threshold) {
			first_wager -= threshold;
			second_wager += threshold;
		}
		
		if (first_wager > threshold)
		{
			//can win, calculate the threshold
			float last_first_wager = first_wager, last_second_wager = second_wager;
			while(((first_wager * first_pei) > wager && second_pei * second_wager > wager) &&
			first_wager * first_pei - second_wager * second_pei > threshold)
			{
				last_first_wager = first_wager;
				last_second_wager = second_wager;
				first_wager -= threshold;
				second_wager += threshold;
			}
			
			if (((first_wager * first_pei) > wager && second_pei * second_wager > wager))
			{
				//can win, get the thresold
				if (Math.abs((first_pei * first_wager - wager) - (second_pei * second_wager - wager)) > 
				Math.abs((last_first_wager *first_pei - wager) - (last_second_wager * second_pei - wager)))
				{
					//get the more steady
					System.out.println("First wager is " + last_first_wager);
					System.out.println("Second wager is " + last_second_wager);
					System.out.println("The profit is " + (last_first_wager *first_pei - wager) + " and " + (last_second_wager * second_pei - wager));
				}
				else					
				{
					System.out.println("First wager is " +  first_wager);
					System.out.println("Second wager is " +  second_wager);
					System.out.println("The profit is " + ( first_wager *first_pei - wager) + " and " + ( second_wager * second_pei - wager));

				}
			}
			else
			{
				//cannot win
				System.out.println("First wager is " + last_first_wager);
				System.out.println("Second wager is " + last_second_wager);
				System.out.println("The profit is " + (last_first_wager *first_pei - wager) + " and " + (last_second_wager * second_pei - wager));

			}
		}
		else
		{
			//cannot win
			System.out.println("Cannot win at all.");
		} 
	}

}
