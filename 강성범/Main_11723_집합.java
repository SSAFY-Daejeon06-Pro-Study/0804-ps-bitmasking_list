import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11723_집합 {

	private static final StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int m = Integer.parseInt(br.readLine());

		int value = 0;

		while(m-- > 0) {
			String[] str = br.readLine().split(" ");

			char c = str[0].charAt(0);

			if(c == 'a' && str[0].charAt(1) == 'd') { // add
				int num = Integer.parseInt(str[1]);
				value |= (1<<num);

			}else if(c == 'r') { // remove
				int num = Integer.parseInt(str[1]);
				value &= ~(1<<num);

			}else if(c == 'c') { // check
				int num = Integer.parseInt(str[1]);
				if((value & (1<<num)) == (1<<num)) {
					sb.append(1);
				}else {
					sb.append(0);
				}
				sb.append(System.lineSeparator());

			}else if(c == 't') { // toggle
				int num = Integer.parseInt(str[1]);
				value ^= (1<<num);

			}else if(c == 'a') { // all
				value = (1<<21)-1;

			}else { //empty
				value = 0;

			}
		}

		System.out.println(sb.toString());

		br.close();
	}
}
