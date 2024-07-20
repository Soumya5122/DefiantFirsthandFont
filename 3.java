import java.util.Arrays;

class BIT {
    int[] bit;
    int n;

    BIT(int n) {
        this.n = n;
        this.bit = new int[n + 1];
    }

    void update(int idx, int val) {
        while (idx <= n) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }

    int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & -idx;
        }
        return sum;
    }
}

class Solution {
    int[] constructLowerArray(int[] arr) {
        int n = arr.length;
        int[] lowerArray = new int[n];
        int[] temp = new int[n];

        // Create a temporary array and copy the elements of the original array
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }

        // Sort the temporary array
        Arrays.sort(temp);

        // Create a BIT
        BIT bit = new BIT(n + 1);

        for (int i = n - 1; i >= 0; i--) {
            // Find the index of the current element in the sorted array
            int idx = Arrays.binarySearch(temp, arr[i]);

            // Update the BIT with the index
            bit.update(idx + 1, 1);

            // Query the BIT to find the number of smaller elements
            lowerArray[i] = bit.query(idx);
        }

        return lowerArray;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {12, 1, 2, 3, 0, 11, 4};
        int[] result = solution.constructLowerArray(arr);

        // Print the result
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}