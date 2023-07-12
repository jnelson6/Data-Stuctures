/**
 * 
 */

/**
 * @author Julia Nelson 
 * I pledge my honor that I have abided by the Stevens Honor System. jnelson6@stevens.edu
 *
 */
public class BinaryNumber {
	int internalValue[];
	
	
	public BinaryNumber(int length)
	{
		internalValue = new int[length];
		
	}
	public BinaryNumber(String str) 
	{
		internalValue = new int[str.length()];
		for(int i=0; i < str.length(); i++)
		{
			internalValue[i] = Character.getNumericValue(str.charAt(i));
		}
	}
	public int getLength()
	{
		return internalValue.length;
	}
	public int[] getInnerArray()
	{
		return internalValue;
	}
	int getDigit(int index)
	{
		if(index >= internalValue.length)
		{
			System.out.println("Invalid value entered");
			return -1;
		}
		return internalValue[index];
	}
	int toDecimal()
	{
		int value = 0;
		int index = 0;
		for(int i=internalValue.length - 1; i >= 0; i--)
		{
			if (internalValue[i] == 1)
			{
				value += Math.pow(2, index);
			}
			index +=1;
		}
		return value;
	}
	void bitShift(int direction, int amount)
	{
		if (direction != 1 && direction != -1)
		{
			System.out.println("direction invalid");
			return;
		}
		if(direction == -1)
		{
			append(amount);
		}
		else if(direction == 1) 
		{
			int[] temp = java.util.Arrays.copyOf(internalValue, internalValue.length - amount);
			for(int i = 0; i < temp.length; i++)
			{
				temp[i] = internalValue[i];
			}
			internalValue = temp;
		}
	}
	void prepend(int amount)
	{
		int[] temp = new int[amount + internalValue.length];
		for (int i = amount; i < amount + internalValue.length; i++)
		{
			temp[i] = internalValue[i];
		}
		internalValue = temp;
	}
	void append(int amount)
	{
		int[] temp = new int[amount + internalValue.length];
		for(int i = 0; i < internalValue.length; i++)
		{
			temp[i] = internalValue[i];
		}
		internalValue = temp;

	}
	static int[] bwor(BinaryNumber bn1, BinaryNumber bn2)
	{
		int[] temp = new int[bn1.getLength()];
		if (bn1.getLength() != bn2.getLength())
		{
			System.out.println("Invalid lengths");
			return new int[0];
		}
		for(int i = 0; i < bn1.getLength(); i++)
		{
			temp[i] = bn1.getDigit(i) | bn2.getDigit(i);
		}
		return temp;
	}
	static int[] bwand(BinaryNumber bn1, BinaryNumber bn2)
	{
		int[] temp = new int[bn1.getLength()];
		if (bn1.getLength() != bn2.getLength())
		{
			System.out.println("Invalid lengths");
			return new int[0];
		}
		for(int i = 0; i < bn1.getLength(); i++)
		{
			temp[i] = bn1.getDigit(i) & bn2.getDigit(i);
		}
		return temp;
	}
	public String toString()
	{
		String result = "";
		for(int i = 0; i < internalValue.length; i++)
		{
			result += Integer.toString(internalValue[i]);
		}
		return result;
	}
	
	void add(BinaryNumber aBinaryNumber)
	{
		
	}
}

