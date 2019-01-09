/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
public class Solution {
    ArrayList<Integer> list=new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //add value recursively into ArrayList
        if(listNode!=null){
            list.add(listNode.val);
            this.printListFromTailToHead(listNode.next);       
        }
        return list;
    }
} 