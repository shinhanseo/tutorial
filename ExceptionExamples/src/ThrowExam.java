
public class ThrowExam {

	public static void arrayMethod() throws ArrayIndexOutOfBoundsException {
		String[] irum = new String[3];
		irum[3] = "ȫ�浿";
	}
	public static void main(String[] args) {
		ThrowExam ts = new ThrowExam();
		try{
			arrayMethod();
			
		}catch(ArrayIndexOutOfBoundsException ae) {
			System.out.println("�迭 ���� �߻�");
		}
		
	}

}
