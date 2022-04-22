package it.register.exercise;

import java.util.Arrays;

public class Exercise2App {

	public static void main(String[] args) {
		//initialize variables
		int multiplicand[]=new int[5];
		int multiplicator[]=new int[5];
		//2*15
		multiplicand[0]=2;
		multiplicator[0]=5;
		multiplicator[1]=1;
		arrayMultiply(multiplicand, multiplicator);		
		//15*2
		multiplicand[0]=5;
		multiplicand[1]=1;
		multiplicator[0]=2;
		multiplicator[1]=0;
		arrayMultiply(multiplicand, multiplicator);
		//19*16
		multiplicand[0]=9;
		multiplicand[1]=1;
		multiplicator[0]=6;
		multiplicator[1]=1;
		arrayMultiply(multiplicand, multiplicator);
		//99*19
		multiplicand[0]=1;
		multiplicand[1]=9;
		multiplicator[0]=9;
		multiplicator[1]=9;
		arrayMultiply(multiplicand, multiplicator);
	}


	/*
	 * simple explanation of the implementation process for 91x99 calculation 

       5       4       3       2       1       0	array index

                                       9       1	x
                                       9       9

                               8       1       9	iteration 1 9x91 partialResult
                       8       1       9        	iteration 2 9x91 partialResult shifted

                       9       0       0       9	sum with carry and recursion
	 */
	private static void arrayMultiply(int[] multiplicand, int[] multiplicator) {
		int resultOfMultiply[]=new int[10];
		
		for (int i = 0; i < multiplicator.length; i++) {
			int[] partialResult = new int[5];
			for (int j = 0; j < multiplicand.length; j++) {
				int total = multiply(multiplicator[i],multiplicand[j]);
				if (total>0 && total<=9) {
					partialResult[j]=total;
				} else {
					//if have more then one digits split by single digit
					int[] totalArray = toArrayDigitsReversed(total);
					//execute array sum
					arrayAddition(totalArray, partialResult, 0,j);
				}
			}
			arrayAddition(partialResult, resultOfMultiply, 0,i);
		}
		
		//print result
		System.out.println(Arrays.toString(resultOfMultiply));
		for(int i=resultOfMultiply.length-1;i>=0;i--) {
			System.out.print(resultOfMultiply[i]);
		}
		System.out.println();
	}

	
	private static void arrayAddition(int[] arrayToSum, int[] resultArray, int increment, int shift) {
		if (increment < arrayToSum.length) {
			int sum = resultArray[increment+shift]+arrayToSum[increment];
			if (sum<=9) {
				resultArray[increment+shift] = sum;
			} else {
				int[] totalArray = toArrayDigitsReversed(sum);
				resultArray[increment+shift] = totalArray[0];
				int carry=totalArray[1];
				arrayToSum[increment+1] = arrayToSum[increment+1] + carry; 
			}
			arrayAddition(arrayToSum, resultArray, increment+1,shift);
		}
	} 
	
	private static int[] toArrayDigitsReversed(int total) {
		int[] intTab = toArrayDigits(total);
		return reverse(intTab,intTab.length);
	}
	
	public static int multiply(int num1, int num2) {
	    if (num1 == 0 || num2 == 0) {
	        return 0;
	    }
	    else if(num2 > 0) {
	        return num1 + multiply(num1, num2 - 1);
	    }
	    else {
	        return -num1 + multiply(num1, num2 + 1);
	    }
	}


	private static int[] toArrayDigits(int multResult) {
		return String.valueOf(multResult).chars().map(Character::getNumericValue).toArray();
	}

	private static int[] reverse(int a[], int n){
		int[] b = new int[n];
		int j = n;
		for (int i = 0; i < n; i++) {
			b[j - 1] = a[i];
			j = j - 1;
		}
		return b;
	}

}
