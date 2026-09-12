import java.util.*;

class Solution {
    static class State implements Comparable<State> {
        long weight;
        List<Integer> ids;

        State(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }

        @Override
        public int compareTo(State o) {
            if (this.weight != o.weight) {
                return Long.compare(o.weight, this.weight); // Max weight first
            }
            for (int i = 0; i < Math.min(this.ids.size(), o.ids.size()); i++) {
                int cmp = Integer.compare(this.ids.get(i), o.ids.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(this.ids.size(), o.ids.size());
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, Comparator.comparingInt(i -> intervals.get(i).get(1)));

        int[] rights = new int[n];
        for (int i = 0; i < n; i++) rights[i] = intervals.get(order[i]).get(1);

        State[] prev = new State[n + 1];
        for (int i = 0; i <= n; i++) prev[i] = new State(0, new ArrayList<>());

        for (int k = 1; k <= 4; k++) {
            State[] cur = new State[n + 1];
            cur[0] = new State(0, new ArrayList<>());
            
            for (int p = 1; p <= n; p++) {
                int i = order[p - 1];
                int l = intervals.get(i).get(0);
                long w = intervals.get(i).get(2);

                int j = binarySearch(rights, l);

                List<Integer> newIds = new ArrayList<>(prev[j].ids);
                newIds.add(i);
                Collections.sort(newIds);
                State take = new State(prev[j].weight + w, newIds);

                cur[p] = cur[p - 1].compareTo(take) <= 0 ? cur[p - 1] : take;
            }
            prev = cur;
        }

        int[] res = new int[prev[n].ids.size()];
        for (int i = 0; i < res.length; i++) res[i] = prev[n].ids.get(i);
        return res;
    }

    private int binarySearch(int[] rights, int target) {
        int l = 0, r = rights.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (rights[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}