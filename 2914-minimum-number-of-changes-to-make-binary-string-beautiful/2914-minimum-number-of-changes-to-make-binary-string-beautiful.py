class Solution(object):
    def minChanges(self, s):
        """
        :type s: str
        :rtype: int
        """
        changes = 0
        # Iterate through the string stepping by 2
        for i in range(0, len(s), 2):
            # If adjacent characters are different, we need 1 change
            if s[i] != s[i+1]:
                changes += 1
                
        return changes
        