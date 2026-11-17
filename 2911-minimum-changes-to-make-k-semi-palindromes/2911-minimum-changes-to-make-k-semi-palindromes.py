class Solution(object):
    def minimumChanges(self, s, k):
        """:type s: str
        :type k: int
        :rtype: int
        """
        n = len(s)
        INF = float('inf')
        
        # Precompute the cost to make substring s[i...j] a semi-palindrome
        # cost[i][j] stores the minimum changes for s[i...j]
        cost = [[INF] * n for _ in range(n)]
        
        for i in range(n):
            for j in range(i + 1, n):
                length = j - i + 1
                min_changes = length
                
                # Check all proper divisors d of length (1 <= d < length)
                for d in range(1, length):
                    if length % d == 0:
                        changes = 0
                        # For each group determined by divisor d
                        for offset in range(d):
                            left = i + offset
                            right = i + ((length - 1) // d) * d + offset
                            
                            while left < right:
                                if s[left] != s[right]:
                                    changes += 1
                                left += d
                                right -= d
                                
                        min_changes = min(min_changes, changes)
                cost[i][j] = min_changes

        # dp[i][rem] = min changes to partition s[i...n-1] into rem semi-palindromes
        dp = [[-1] * (k + 1) for _ in range(n)]

        def solve(idx, rem):
            if rem == 1:
                return cost[idx][n - 1]
            if dp[idx][rem] != -1:
                return dp[idx][rem]
            
            min_changes = INF
            # Each of the 'rem' parts needs at least 2 characters.
            # Current part s[idx...end] has length >= 2 => end >= idx + 1
            # Remaining (rem - 1) parts need at least 2 * (rem - 1) chars => end <= n - 2 * (rem - 1) - 1
            for end in range(idx + 1, n - 2 * rem + 2):
                current_cost = cost[idx][end]
                remaining_cost = solve(end + 1, rem - 1)
                min_changes = min(min_changes, current_cost + remaining_cost)
                
            dp[idx][rem] = min_changes
            return min_changes

        return solve(0, k)