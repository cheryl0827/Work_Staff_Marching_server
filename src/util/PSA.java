package util;
import java.util.Random;
public class PSA {
	public static int[][] num1;//=new int[][]{{5,6,6,5,5,6},{3,4,6,3,3,4},{6,6,6,6,6,6},{5,6,6,5,5,6},{7,7,5,7,7,7},{5,6,8,5,5,6}};//�������
	public int shiyingzhi(int []a ,int[][] num1) {
		int z=0;
		for (int i = 1; i < num1.length; i++) {
			z=z+num1[a[i-1]][a[i]];
			
		}
		return z;
		
	}
	public int[] linyu2(int [] a) {
		int []a1=new int[a.length];
		int []a2=new int[a.length];
		PSA test=new PSA();
		int z1=test.shiyingzhi(a, num1);
		int rad1=(int) (Math.random()*a.length);
		int rad2=(int) (Math.random()*a.length);
		while (rad1==rad2) {
			rad2=(int) (Math.random()*a.length);
		}
		for (int j = 0; j < a.length; j++) {
			a1[j]=a[j];
		}
		int temp2=a1[rad2];
		a1[rad2]=a1[rad1];
		a1[rad1]=temp2;
		int z2=test.shiyingzhi(a1, num1);
		rad1=(int) (Math.random()*a.length);
		rad2=(int) (Math.random()*a.length);
		while (rad1==rad2) {
			rad2=(int) (Math.random()*a.length);
		}

		for (int j = 0; j < a.length; j++) {
			a2[j]=a[j];
		}
		temp2=a2[rad2];
		a2[rad2]=a2[rad1];
		a2[rad1]=temp2;

		int z3=test.shiyingzhi(a2, num1);

		if (z1<=z2) {
			if(z1<=z3)
			{
				return a;//z1��С
			}
			else {
				return a2;//z3��С
			}
		} else if(z2<=z3){
				return a1;//z2��С
		}
		else {
			    return a2;//z3��С
		}
			
			
	}
	
	
	public int[] bsss(int [][] a)
	{
		int result[];
		int numCount= 2;
		int numLevel=5;
		int cthreshold=1;
		int lthreshold=1;
		int lhighThreshold=4;
		int counter=0;
		int l=0;
		int []z=new int [numLevel+1] ;
		//定义参数
		PSA test=new PSA();
	
		int[][] intArray=new int [numLevel+1][a.length];
		result=new int[a.length+1];
		
		Random rand = new Random();
		int i,count=1;
		intArray[0][0]=(int)(Math.random()*a.length);
		while (count<a.length) {
			int temp1=(int)(Math.random()*a.length);
			boolean flag=true;
			for (i=0;i<count;i++) {
				if(temp1==intArray[0][i]){
					flag=false;
					break;
				}
			}
			if (flag==true) {
				intArray[0][count]=temp1;
				count++;
			}
		}
		//本人注释System.out.println("第一列：");
		for (int j=0;j<a.length;j++) {
			//本人注释System.out.print(intArray[0][j]+" ");//随即初始点
		}
		
		z[0]=test.shiyingzhi(intArray[0], a);
		//初始点适应值
				//本人注释System.out.println("适应值："+z[0]);
		for(int i1=1;i1<numLevel+1;i1++)
		{
		
		int rad1=(int) (Math.random()*a.length);
		int rad2=(int) (Math.random()*a.length);
		while (rad1==rad2) {
			rad2=(int) (Math.random()*a.length);
		}
		
		for (int j = 0; j < a.length; j++) {
			intArray[i1][j]=intArray[i1-1][j];
		}
		int temp2=intArray[i1][rad2];
		intArray[i1][rad2]=intArray[i1][rad1];
		intArray[i1][rad1]=temp2;
		
		for (int j=0;j<intArray[i1].length;j++) {
			//����ע��System.out.print(intArray[i1][j]+" ");//�漴��ʼ��
		}
		
		z[i1]=test.shiyingzhi(intArray[i1], a);
		//����ע��System.out.println("��Ӧֵ��"+z[i1]);
		
		}

		//����ע��System.out.println("�ڶ��У�������ȡ������ȡ��С��Ӧֵ����");
		for (int i1 = 0; i1 < numLevel+1; i1++) {
			
		
		intArray[i1]=test.linyu2(intArray[i1]);
	
		for (int j=0;j <a.length;j++) {
			//����ע��System.out.print(intArray[i1][j]+" ");//�漴��ʼ��
		}
		z[i1]=test.shiyingzhi(intArray[i1], a);
		//����ע��System.out.println("��Ӧֵ��"+z[i1]);
		}

		
		//����ע��System.out.println("�����У�������ȡ������ȡ��С��Ӧֵ����");
for (int i1 = 0; i1 <numLevel+1; i1++) {
	
	
	intArray[i1]=test.linyu2(intArray[i1]);

	for (int j=0;j <a.length;j++) {
	//����ע��	System.out.print(intArray[i1][j]+" ");//�漴��ʼ��
	}
	z[i1]=test.shiyingzhi(intArray[i1], a);
	//����ע��System.out.println("��Ӧֵ��"+z[i1]);
	}

		int min = z[0];  
        int index = 0;  
        for (int n = 1; n < z.length; n++ )  
        {  
            if (z[n] < min)  
            {  
                min = z[n];  
                index = n;  
            }  
        }    
      //����ע��System.out.println("��СֵΪ��" + min);  
        for (int j=0;j <a.length;j++) {
        		//System.out.print(intArray[index][j]+" ");
        		result[j]=intArray[index][j];
		}
        result[a.length]=min;

      //  return intArray;
        return result;
		//return min;
		
	}
}
