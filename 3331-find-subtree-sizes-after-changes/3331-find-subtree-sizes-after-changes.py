class Solution(object):
    def findSubtreeSizes(self, parent, s):
        """
        :type parent: List[int]
        :type s: str
        :rtype: List[int]
        """
        n = len(parent)
        orig_children = [[] for _ in range(n)]
        for node in range(1, n):
            orig_children[parent[node]].append(node)

        new_parent = parent[:]
        closest_ancestor = [-1] * 26

        def assign_new_parents(u):
            idx = ord(s[u]) - ord('a')
            if closest_ancestor[idx] != -1:
                new_parent[u] = closest_ancestor[idx]

            prev = closest_ancestor[idx]
            closest_ancestor[idx] = u

            for v in orig_children[u]:
                assign_new_parents(v)

            closest_ancestor[idx] = prev

        assign_new_parents(0)

        new_children = [[] for _ in range(n)]
        for node in range(1, n):
            new_children[new_parent[node]].append(node)

        ans = [1] * n

        def compute_sizes(u):
            total = 1
            for v in new_children[u]:
                total += compute_sizes(v)
            ans[u] = total
            return total

        compute_sizes(0)
        return ans