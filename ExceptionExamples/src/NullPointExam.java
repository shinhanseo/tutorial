
public class NullPointExam {

	public static void main(String[] args) {
		String str = null;
		try {
		System.out.println("���ڿ�: " + str.length());
     
	}catch(NullPointerException e) {
		e.getMessage();
	}

	}
	}
