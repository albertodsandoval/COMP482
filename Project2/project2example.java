// John Noga
// 2/25/2025
// Not an acceptable solution to Proj2 for many reasons:
// 1) is O(N) 2) doesn't read from file 3) output format incorrect 4) ...
// But it does use Dynamic Programming
public class Proj2WithDP {
public static void main(String[] args) {
int N=101; // change to get answer for larger ranges
int[] answer = new int[N];
answer[0] = 1; answer[1] = 0;
for (int i=2; i<N; i++) {
if (i%2==0)
answer[i] = 1+answer[i/2];
else
answer[i] = 1 + Math.min(answer[i-1],1+answer[(i+1)/2]);
}
for (int i=0; i<N; i++)
System.out.println(i+ " " + answer[i]);
}
}

