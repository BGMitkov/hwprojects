import java.util.LinkedList;
import java.util.Arrays;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
/**
 * 
 * @author Borislav Mitkov
 *
 */
public class FileEncoder61668 implements FileEncoderFN {
	
	@Override
	public  void encode(String sourceFile, String destinationFile,LinkedList<Character> key) 
	{
		fillPrimes();	//������ �������� �����, ��� ����� ���� �� �� ��������.
			File infile = new File(sourceFile);
			File outfile = new File(destinationFile);
			
			if (!infile.exists()) //������� �����, ��� �� ����������.
			{
				try 
				{
					outfile.createNewFile();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			
			try(FileInputStream fip = new FileInputStream(infile);FileOutputStream fop = new FileOutputStream(outfile))
			{
				for(int i=0;i<infile.length();i++) //������
				{
					char b = (char)fip.read();
					if(primes[i]) fop.write(b);
					else fop.write(key.get(b));
				}
			fip.close();
			fop.close();
			} 	
			catch (IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("EncodingDone");
	}

	@Override
	public void decode(String sourceFile, String destinationFile,LinkedList<Character> key) 
	{
		fillPrimes();	//������ �������� �����, ��� ����� ���� �� ��������.
		File efile = new File(sourceFile);
		File dfile = new File(destinationFile);
		
		if (!dfile.exists()) //������� ����� ��� �� ����������
		{
			try 
			{
				dfile.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try(FileInputStream fip = new FileInputStream(efile);
				FileOutputStream fop = new FileOutputStream(dfile))
		{
			for(int i=0;i<efile.length();i++) //������
			{
				char b = (char)fip.read();
				if(primes[i]) fop.write(b);
				else fop.write(key.indexOf(b));
			}
			fip.close();
			fop.close();
		}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		System.out.println("DecodingDone");
	}

	static boolean  primes[] = new boolean[1000000]; //�����, ����� ������ �������� true
													//��� ������� � ������ �����, � false � �������� ������
	//��������� ��������
	public static void fillPrimes() 
	{	
		if(primes[1]==false) //��������� ���� ������ � ��������, ��� �� � ��������� ���������, 
		{					//��� � (�.�. 1-���� ���� ��� �������� �� � ������), �� ���������
			Arrays.fill(primes,true);        // �������� ������ ����� �� �� ������
			primes[0]=false; 			    // 0 �� � ������
				
			for (int i=2;i<primes.length;i++) 
			{
				//��� ������� � ������, 
				//�� ����������� ������ ������ ������ � �� ���� �������� false.
				if(primes[i]) 
				{
					for (int j=2;i*j<primes.length;j++) 
					{
						primes[i*j]=false;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {}
}
