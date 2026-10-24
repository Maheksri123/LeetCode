class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int arriveAliceDays = convertToDays(arriveAlice, daysInMonths);
        int leaveAliceDays = convertToDays(leaveAlice, daysInMonths);
        int arriveBobDays = convertToDays(arriveBob, daysInMonths);
        int leaveBobDays = convertToDays(leaveBob, daysInMonths);
        
        int overlapStart = Math.max(arriveAliceDays, arriveBobDays);
        int overlapEnd = Math.min(leaveAliceDays, leaveBobDays);
        
        // If overlapStart <= overlapEnd, they spent (overlapEnd - overlapStart + 1) days together.
        // Otherwise, they didn't overlap.
        return Math.max(0, overlapEnd - overlapStart + 1);
    }
    
    private int convertToDays(String date, int[] daysInMonths) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        
        return totalDays;
    }
}