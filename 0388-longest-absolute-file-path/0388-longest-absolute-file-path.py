class Solution:
    def lengthLongestPath(self, input: str) -> int:
        max_len = 0
        # path_length[depth] stores the current path length at given depth
        # depth 0 maps to path_length[0]
        path_length = {0: 0}
        
        for line in input.split('\n'):
            name = line.lstrip('\t')
            depth = len(line) - len(name)
            
            # If it's a file (contains a dot)
            if '.' in name:
                # Total length = length up to parent + file name length
                # Note: path_length[depth] already includes slashes from parents
                max_len = max(max_len, path_length[depth] + len(name))
            else:
                # If it's a directory, update path length for the next level (+1 for '/')
                path_length[depth + 1] = path_length[depth] + len(name) + 1
                
        return max_len