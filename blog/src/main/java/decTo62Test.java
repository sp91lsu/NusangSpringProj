
public class decTo62Test {

	String pre="";
	
	String decTo62(int dec) {
		String c62 = null;
			String myPre=pre;
			pre+="\t";
			System.out.println(myPre+"dec: "+dec);
			System.out.println(myPre+"div: "+62);
		char[] key= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        int quo = (int)(dec/62);
        System.out.println(myPre+"quo: "+quo);
        int rem = dec%62;
        	System.out.println(myPre+"rem: "+rem);
        c62 = Character.toString(key[rem]);
        	System.out.println(myPre+"c62: "+c62);
        if (quo!=0) {
        	c62 = decTo62(quo)+c62;
        		System.out.println(myPre+"c62: "+c62);
        }
		return c62;
	}
	public static void main(String[] args) {
		decTo62Test t = new decTo62Test();
//		for (int i = 0; i < 125; i++) {
//			
//			System.out.println(""+i+":"+t.decTo62(i));
//		}
		int i = 12456;
		System.out.println("\n"+i+": "+t.decTo62(i));
	}

}
