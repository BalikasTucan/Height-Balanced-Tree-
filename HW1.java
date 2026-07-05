/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

import java.util.Scanner;

public class HW1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HBTree tree = new HBTree();

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("-i")) {
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid Command!");
                    continue;
                }

                int x = sc.nextInt();
                boolean inserted = tree.insert(x);

                if (inserted) {
                    System.out.println();
                    System.out.println(x + " I");
                } else {
                    System.out.println();
                    System.out.println(x + " NI");
                }
            }

            else if (command.equals("-d")) {
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid Command!");
                    continue;
                }

                int x = sc.nextInt();
                boolean deleted = tree.delete(x);

                if (deleted) {
                    System.out.println();
                    System.out.println(x + " D");
                } else {
                    System.out.println();
                    System.out.println(x + " ND");
                }
            }

            else if (command.equals("-f")) {
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid Command!");
                    continue;
                }

                int x = sc.nextInt();
                boolean found = tree.find(x);

                if (found) {
                    System.out.println();
                    System.out.println(x + " F");
                } else {
                    System.out.println();
                    System.out.println(x + " NF");
                }
            }

            else if (command.equals("-p")) {
                System.out.println();
                tree.print();
            }

            else if (command.equals("-q")) {
                break;
            }

            else {
                System.out.println("Invalid Command!");
            }
        }

        sc.close();
    }
}

    

