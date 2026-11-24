class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        final int MAX_LCM = 2520; // lcm(1..10)

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int g = 0;
            int l = 1;

            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);
                prod *= nums[j];

                // lcm(subarray) <= 2520 and gcd(subarray) <= g for all future extensions
                if (prod > (long) MAX_LCM * g) {
                    break;
                }

                if (prod == (long) l * g) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}