// John Noga 
// 3/17/2025
// Generates an input file for Project 3 

import java.io.*;
import java.util.*;

public class InputGenerator3 {

   public static void main(String[] args) throws IOException {
      Scanner input = new Scanner(System.in);
      Random r = new Random(0);
      BufferedWriter file = new BufferedWriter(new FileWriter("input.txt"));
      int k, l, m, kb, lb, mb, ke, le, me;

      System.out.println("Enter k, l, and m");
      k = input.nextInt(); 
      l = input.nextInt(); 
      m = input.nextInt();
      file.write(k+" "+l+" "+m+"\n\n");

      kb = r.nextInt(k/4+1)+k/4; 
      lb = r.nextInt(l/4+1)+l/4; 
      mb = r.nextInt(m/4+1)+m/4;
      ke = r.nextInt(k/4)+k/2;
      le = r.nextInt(l/4)+l/2;
      me = r.nextInt(m/4)+m/2;

      for (int ii=0; ii<k; ii++){
         for (int jj=0; jj<l; jj++) {
            for (int kk=0; kk<m; kk++){
               if (kb<=ii && ii<=ke && lb<=jj && jj<=le && mb<=kk && kk<=me)
                  file.write("1 ");
               else
                  file.write("0 ");
            }
            file.write("\n");
         }
         file.write("\n");
      }
      file.close();
      System.out.println((ke-kb+1)*(le-lb+1)*(me-mb+1));
   }
}

