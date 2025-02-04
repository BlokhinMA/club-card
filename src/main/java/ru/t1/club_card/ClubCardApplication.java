package ru.t1.club_card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

//@SpringBootApplication
public class ClubCardApplication {

    public static void main(String[] args) {
		//SpringApplication.run(ClubCardApplication.class, args);
		Scanner scanner = new Scanner(System.in);
		int A = scanner.nextInt(), B = scanner.nextInt(), C = scanner.nextInt(), D = scanner.nextInt();
		scanner.close();



	}

	/*public static int scan(Integer sc) {
		Scanner in = new Scanner(System.in);
		while (sc == null) {
			if (in.hasNextInt())
				sc = in.nextInt();
			else {
				in.nextLine();
				System.out.println("Error");
			}
		}
		return sc;
	}*/

}
