package assignment.pkg1;
import java.util.Arrays;
import java.util.Scanner;
public class Assignment1 {
    
    public static void main(String[] args) {
          Scanner arrayElements = new Scanner(System.in);
          System.out.print("Enter Size of Array: ");
          int size = arrayElements.nextInt();
          int[] A = new int[size];
          System.out.println("Enter the elements of array");
          for (int i = 0; i < A.length; i++) {
             A[i] = arrayElements.nextInt();
          }
          System.out.println("The UNSORTED array elements are: " + Arrays.toString(A));
          int p = 0, r = size-1;
          mergeSort(A, p, r);
          System.out.println("The SORTED array elements are: " + Arrays.toString(A));
   }

   public static void mergeSort(int[] A, int p, int r) {
      if (p < r) {
         int q = (p + r) / 2;
         mergeSort(A, p, q);
         mergeSort(A, q + 1, r);
         merge(A, p, q, r);
      }
   }

   public static void merge(int[] A, int p, int q, int r) {
      int n1 = q - p + 1;
      int n2 = r - q;
      int[] L = new int[n1 + 1];
      int[] R = new int[n2 + 1];
      L[n1] = Integer.MAX_VALUE;
      R[n2] = Integer.MAX_VALUE;
      for (int i = 0; i < n1; i++) {
         L[i] = A[p + i];
      }
      for (int j = 0; j < n2; j++) {
         R[j] = A[q + j + 1];
      }
      int x = 0, y = 0;
      for (int k = p; k <= r; k++) {
         if (L[x] <= R[y]) {
            A[k] = L[x];
            x++;
         } else {
            A[k] = R[y];
            y++;
         }
      }   
    }
}