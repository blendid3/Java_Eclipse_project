package test2;
//import com.google.common.primitives.Ints;
import java.io.*; 
import java.lang.*; 
import java.util.*; 
import java.lang.Object.*;
import java.util.stream.Collectors;
public class Main{

    public static String intToRoman(int num) {
    	int[] array= {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        HashMap<Integer,String> romanMap= new HashMap<Integer,String>();
        romanMap.put(1000, "M");romanMap.put(900, "CM");romanMap.put(500, "D");
        romanMap.put(400, "CD");romanMap.put(100, "C");romanMap.put(90, "XC");
        romanMap.put(50, "L");romanMap.put(40, "XL");romanMap.put(10, "X");
        romanMap.put(9, "IX");romanMap.put(5, "V");romanMap.put(4, "IV");
        romanMap.put(1, "I");
        StringBuilder str=new StringBuilder();
        for(int i=0;i<array.length;i++) {
        	int time=num/array[i];
        	num-=array[i]*time;
        	while(time!=0)
        	{
        		str.append(romanMap.get(array[i]));
        		time--;
        	}

        }
        return str.toString();
    }
    
    public static int romanToInt(String s) {
    	HashMap<Character, Integer> map=new HashMap<Character, Integer>();
    	map.put('M',1000);map.put('D',500);map.put('C',100);map.put('L',50);
    	map.put('X',10);map.put('V',5);map.put('I',1);
    	int sum=0;
    	for(int i=0;i<s.length();i++) {
    		int val=map.get(s.charAt(i));
    		if(i!=s.length()-1&&val<map.get(s.charAt(i+1)))
    			sum-=val;
    		else sum+=val;
    		
    	}
        return sum;
    }
    
    public static String longestCommonPrefix(String[] strs) {
    	int minLen=1000;
    	int pre_num=-1;
    	for(int i=0;i<strs.length;i++) {
    		int len=strs[i].length();
    		if(len<minLen)
    			minLen=len;
    	}
    	for(int pos=0;pos<minLen;pos++)
    	{
    		int end_flag=0;
    		char test_char='0';
    		for(int str_num=0;str_num<strs.length;str_num++)
    		{
    			if(test_char!='0')
    			{
    				if(test_char!=strs[str_num].charAt(pos))
    				{
    					end_flag=1; break;
    				}
    			} else test_char=strs[str_num].charAt(pos);
    		}
    		if(end_flag==1) break;
    		pre_num=pos; 
    	}
    	if(pre_num==-1)
    		return "";
        return strs[0].substring(0, pre_num+1);
    }
    
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result=new ArrayList<List<Integer>>() ;
    	Arrays.sort(nums);
        int pre_left=1000;
        
    	for(int left=0;left<nums.length-2;left++) {
            if(pre_left==1000) pre_left=nums[left];
            else 
            	if(pre_left==nums[left]){continue;} else pre_left=nums[left];
    		int val=-1*nums[left];
    		int medium=left+1;
    		int right=nums.length-1;
    		int pre_middle=1000;
    		while(right>medium)
    		{
    			if(nums[medium]+nums[right]<val)
    				medium++;
    			else if(nums[medium]+nums[right]>val)
    				right--;
    			else {
    				if(pre_middle==1000) pre_middle=nums[medium]; else if(pre_middle==nums[medium]) {medium++;continue;}
    				List<Integer> list=new ArrayList<Integer> ();
    				list.add(nums[left]);list.add(nums[medium]);list.add(nums[right]);
    				result.add(list);
    				medium++;
    			}
    		}
    	}
    	return result;
    	
    }
    public static int threeSumClosest(int[] nums, int target) {
    	int left=0;
    	int min_dif=Integer.MAX_VALUE;
    	int res=0;
    	Arrays.sort(nums);
    	for(left=0;left<nums.length-2;left++)
    	{
    		int medium=left+1;
    		int right=nums.length-1;
    		int result=nums[left]+nums[medium]+nums[medium+1];
    		if(result==target) return target;
    		if(result>target) {
    			int dif=Math.abs(result-target); 
    			if(dif<min_dif) {
    				min_dif=dif;res=result;
    			}
    			continue;
    		}
    		result=nums[left]+nums[right-1]+nums[right];
    		if(result==target) return target;
    		if(result<target) {
    			int dif=Math.abs(result-target); 
    			if(dif<min_dif) {
    				min_dif=dif;res=result;
    			}
    			continue;
    		}
    		
    		while(medium<right) {
    			
    			result=nums[left]+nums[medium]+nums[right];
    			int dif=(result-target); 
    			if(dif==0) return target;
    			if(dif<0) medium++; else right--;
    			dif=Math.abs(dif); 
    			if(dif<min_dif) {
    				min_dif=dif;res=result;
    			}
    		}
    	}
        return res;
    }
    static List<String>  real_list=new ArrayList<String>();
    public static final Map<String, String> cell_board = new HashMap<String, String>() {
        {
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }
    };
    public static void dfs(StringBuilder str,StringBuilder digits) {
    	if(digits.length()==0) {
    		real_list.add(str.toString());
    		return ;
    	} else {
    		String str_list=cell_board.get(String.valueOf(digits.charAt(0)));
    		for(int i=0;i<str_list.length();i++) {
    			StringBuilder str_temp=new StringBuilder(str);
    			str_temp.append(str_list.charAt(i));
    			StringBuilder digits_new=new StringBuilder(digits.substring(1, digits.length()));
    			dfs(str_temp,digits_new);
    		}
    	}	
    }

    
    public static List<String> letterCombinations(String digits) {
    	StringBuilder digits_new=new StringBuilder(digits);
    	StringBuilder str=new StringBuilder();
    	dfs(str,digits_new);
    	
    	return real_list;
    }
    
    public static List<List<Integer>> fourSum(int[] nums, int target) {
    	Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        int prev_left_val=1000;
        for(int left=0;left<nums.length-3;left++) {
        	if(prev_left_val==1000) prev_left_val=nums[left];
        	else if(prev_left_val==nums[left]) continue; else prev_left_val=nums[left];
        	int prev_med1_val=1000;
        	for(int medium_1=left+1;medium_1<nums.length-2;medium_1++) {
            	if(prev_med1_val==1000) prev_med1_val=nums[medium_1];
            	else if(prev_med1_val==nums[medium_1]) continue; else prev_med1_val=nums[medium_1];
            	
            	int medium_2=medium_1+1;int right=nums.length-1;

            	int prev_sum=1000;
            	while(medium_2<right) {
            		int val=nums[left]+nums[medium_1]+nums[medium_2]+nums[right];
                	if(prev_sum==1000) prev_sum=val;
                	else if(prev_sum==val) {
                		if(val>target) right--;else medium_2++;
                		continue;
                	} else 
                		prev_sum=val;
                	

            		if(val>target) right--;
            		else if(val<target) medium_2++; else {
            			List<Integer> list=new ArrayList<Integer>();
            			list.add(nums[left]);list.add(nums[medium_1]);list.add(nums[medium_2]);list.add(nums[right]);
            			res.add(list);
            			medium_2++;
            		}
            	}
            	
        	}
        	
        	
        }
        return res;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	List<ListNode>list1=new ArrayList<ListNode>();
    	
    	ListNode cur_node=head;

    	while(cur_node!=null) {
    		list1.add(cur_node);
    		cur_node=cur_node.next;
    	}
    	int size=list1.size();
    	if(size==1)
    		return null; else
    	if(n==1) list1.get(size-2).next=null; else
    	if(n==size) {head=list1.get(1);} else {
    		list1.get(size-n-1).next=list1.get(size-n+1);
    	}
    	
    	
        return head;
    }
    final static HashMap<Character, Character>map=new HashMap<Character, Character>() {
    	{
    		put('(',')');
    		put('[',']');
    		put('{','}');
    	}
    };
    public static boolean isValid(String s) {
        Stack<Character>s1=new Stack<Character>();
        if(s.length()==0) return true;
        for(int i=0;i<s.length();i++) {
        	if(s1.size()==0)
        		s1.push(s.charAt(i));
        	else if(map.containsKey(s.charAt(i))) {
        		s1.push(s.charAt(i));
        	} else if(Character.compare(s.charAt(i),map.get(s1.peek()) )!=0) {
        		return false;
        	} else {
        		s1.pop();
        	}
        	
        }
        
        if(s1.size()==0)
        return true;
        else return false;
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode head=new ListNode();
    	ListNode cur_node=head;
		if(l1.val>l2.val) {
			cur_node.val=l2.val;
			l2=l2.next;
		}
		else {
			
			cur_node.val=l1.val;
			l1=l1.next;
		} 
    	
    	
    	while(l1!=null||l2!=null) {
    		if(l1==null) {
    			ListNode temp=new ListNode();
    			temp.val=l2.val;
    			cur_node.next=temp;
    			cur_node=temp;
    			l2=l2.next;
    			continue;
    		}
    		if(l2==null) {
    			ListNode temp=new ListNode();
    			temp.val=l1.val;
    			cur_node.next=temp;
    			cur_node=temp;
    			l1=l1.next;
    			continue;
    		}
    		if(l1.val>l2.val) {
    			ListNode temp=new ListNode();
    			temp.val=l2.val;
    			cur_node.next=temp;
    			cur_node=temp;
    			l2=l2.next;
    		}
    			
    		else {
    			ListNode temp=new ListNode();
    			temp.val=l1.val;
    			cur_node.next=temp;
    			cur_node=temp;
    			l1=l1.next;
    		} 
    	}
    	
    	
        return head;
    }
    List<String> strs; 
    public void dfsGenerateParthesis(String ans,int open, int close,int n) {
    	if(open==n&&close==n)
    	{
    		strs.add(ans); return;
    	}
    	if(open==n) {
    		
    		close++;
    		dfsGenerateParthesis(ans+")",open,close,n); return;
    	}
    	if(open==close) {
    		
    		open++;
    		dfsGenerateParthesis(ans+"(",open,close,n);
    	} else {
    		dfsGenerateParthesis(ans+"(",open+1,close,n);
    		dfsGenerateParthesis(ans+")",open,close+1,n);
    	}
    	
    }
    public List<String> generateParenthesis(int n) {
    	strs=new ArrayList<>();
    	String ans="";
    	
    	dfsGenerateParthesis(ans,0,0,n);
    	return strs;
    }
    ListNode l_my;
    ListNode head;
    List<String> strs_test; 
    public void mergeList(List<ListNode> lists) {
    	int min=1000;
    	int min_index=-1;
    	for(int i=0;;) {
    		if(i>=lists.size()) break;
    		ListNode l_temp=lists.get(i);
    		if(l_temp==null) {
//    			lists.rem
    			lists.remove(i); continue;
    		} else {
    			if(l_temp.val<min) {
    				min=l_temp.val; min_index=i;
    			}
    				
    		}
    		i++;
    	}
    	if(lists.size()==0)
    		return;
    	ListNode new_node=lists.get(min_index);
    	lists.set(min_index, new_node.next);

    	l_my.next=new ListNode(min);
    	l_my=l_my.next;
    	mergeList(lists);
    }
    public ListNode mergeKLists(ListNode[] lists) {
    	
    	l_my=new ListNode();
    	head=l_my;
    	List<ListNode> lists1= new ArrayList<ListNode>();
    	for(int i=0;i<lists.length;i++) {
    		lists1.add(lists[i]);
    	}
    	
    	mergeList(lists1);
        return head.next;
    }
    
    public ListNode swapPairs(ListNode head) {
    	List<ListNode> l1=new ArrayList<ListNode>();
    	ListNode l_head=head;
//    	ListNode l1 h1=new ListNode();
    	while(l_head!=null) {
    		l1.add(l_head);
    		l_head=l_head.next;
    	}
    	ListNode head_new=null;
    	ListNode cur_node;
    	ListNode prev_node=null;
    	for(int i=0;i<=l1.size()-1;i+=2) {
    		cur_node=new ListNode();
    		if(head_new==null)
    			head_new=cur_node;
    		if(prev_node!=null)
    			prev_node.next=cur_node;
    		if(i+1>=l1.size()) {
    			cur_node.val=l1.get(i).val;
    			break;
    		}
    			
    		cur_node.val=l1.get(i+1).val;
    		ListNode temp=new ListNode(l1.get(i).val);
    		cur_node.next=temp;
    		cur_node=cur_node.next;
    		prev_node=cur_node;
    	}
    		
        return head_new;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
    	List<ListNode> l1=new ArrayList<ListNode>();
    	ListNode l_head=head;
    	while(l_head!=null) {
    		l1.add(l_head);
    		l_head=l_head.next;
    	}
    	ListNode head_new=null;
    	ListNode cur_node;
    	ListNode prev_node=null;
    	for(int i=0;i<l1.size();i+=k) {
    		cur_node=new ListNode();
    		if(head_new==null)
    			head_new=cur_node;
    		if(prev_node!=null)
    			prev_node.next=cur_node;
    		if(i+k>l1.size()) {
    			ListNode next_node=null;
    			for(int j=i;j<l1.size();j++) {
    				if(next_node!=null) {
    					cur_node.next=next_node;
    					cur_node=next_node;
    				}
    				cur_node.val=l1.get(j).val;
    				next_node=new ListNode();
    			}
    			break;
    		}
    		ListNode next_node=null;
    		for(int j=i+k-1;j>=i;j--) {
				if(next_node!=null) {
					cur_node.next=next_node;
					cur_node=next_node;
				}
    			cur_node.val=l1.get(j).val;
    			next_node=new ListNode();
    		}
    		prev_node=cur_node;

    	}
    	
        return head_new;
    }
    public void fillArray(List<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            array.set(i, i);
        }
    }
    
    public int removeDuplicates(int[] nums) {
    	List<Integer> l1=new ArrayList<Integer>();
    	int prev_val=-1;
    	int num=0;
    	for(int n=0;n<nums.length;n++) {
    		if(prev_val==-1)
    			prev_val=nums[n];
    		else if(prev_val==nums[n]) {
    			continue;
    		} else prev_val=nums[n];
    		l1.add(nums[n]);
    		num++;
    	}
    	nums= l1.stream().mapToInt(i->i).toArray();
//    	Arrays.copyof
        return num;
    }
    
    public int removeElement(int[] nums, int val) {
    	int num=0;
    	for(int i=0;i<nums.length;i++) {
    		if(nums[i]!=val) {
    			nums[num]=nums[i];
    			num++;
    		}
    		
    	}
        return num;
    }
    
    public int strStr(String haystack, String needle) {
        if(haystack.length()==0&&needle.length()==0) return 0;
        if(haystack.length()==0&&needle.length()!=0) return -1;
        if(haystack.length()!=0&&needle.length()==0) return 0;
    	int num=0;
    	for(int i=0;i<haystack.length();i++) {
    		if(haystack.charAt(i)==needle.charAt(num)) {
    			if(num==needle.length()-1) return i-num;
    			num++;
    		} else {
    			i=i-num;
    			num=0;
    		}
    	}
    	
        return -1;
    }
    
    public int divide(int dividend, int divisor) {
        return dividend/divisor;
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
    	if(words.length==0) return new ArrayList<Integer>();
    	HashMap<Integer,String> map=new HashMap<>();
    	HashMap<String,Integer> num_map=new HashMap<>();
    	List<Integer>res=new ArrayList<Integer>();
    	for(int i=0;i<words.length;i++) {
    		if(num_map.containsKey(words[i]))
    			num_map.put(words[i],num_map.get(words[i])+1 );
    		else num_map.put(words[i],1 );
    	}
    	for(int i=0;i<s.length();i++) {
    		
    		for(int j=0;j<words.length;j++) {
    			if(words[j].length()+i>s.length())
    				continue;
    			String sub_str=s.substring(i, words[j].length()+i);
    			if(sub_str.equals(words[j]) )
    				map.put(i, words[j]);
    		}
    	}
    	
    	for(int i=0;i<s.length();i++) {
    		int extend=0;
    		HashMap<String, Integer>tempMap=new HashMap<>();
    		
    		int num=0;
    		for(int j=0;j<words.length;j++) { //4
    			if(map.containsKey(i+extend)) {
    				String str=map.get(i+extend);
    				if(tempMap.containsKey(str)) {
    					if(tempMap.get(str)<num_map.get(str)) {
    						tempMap.put(str, tempMap.get(str)+1);
    					}
    					else
    						break;
    				} else tempMap.put(str, 1);
    				
    				num++;
    				extend+=str.length();
    			}
    		}
    		if(num==words.length)
    			res.add(i);
    	}
    	return res;
    }
    
    public void nextPermutation(int[] nums) {
        for(int i=nums.length-1;i>0;i--) {
        	int temp=0;
        	int temp1=0;
        	if(nums[i-1]<nums[i]) {
        		int min_val=Integer.MAX_VALUE;
        		int min_index=-1;
        		for(int k=nums.length-1;k>=i;k--) {
        			if(nums[k]>nums[i-1]&&nums[k]<min_val) {
        				min_val=nums[k];
        				min_index=k;
        			}
        		}
        		temp=nums[min_index];
        		temp1=nums[i-1];
        		nums[min_index]=temp1;
        		nums[i-1]=temp;
        		Arrays.parallelSort(nums, i, nums.length);
        		return;
        	}	
        }
       
        Arrays.parallelSort(nums);
        
    }
    
    public int longestValidParentheses(String s) {
    	int []dp=new int[s.length()+1];
    	for(int i=0;i<s.length();i++) {
    		if(Character.compare(s.charAt(i), ')')==0) {
    			if(i==0) continue;
    			if(Character.compare(s.charAt(i-1), '(')==0)
    				dp[i+1]=dp[i-2+1]+2;
    			else if(i-dp[i-1+1]-1>=0&&Character.compare(s.charAt(i-dp[i-1+1]-1), '(')==0) {
    				dp[i+1]=dp[i-dp[i-1+1]-2+1]+dp[i-1+1]+2;
    			}
    		}
    	}
    	int max_val=0;
    	
    	for(int i=0;i<dp.length;i++) {
    		if(dp[i]>max_val) 
    			max_val=dp[i];
    	}
        return max_val;
    }
    
    public int search(int[] nums, int target ) {
    	int med; int right; int left;
    	right=nums.length-1;
    	left=0;
    	med=(right+left)/2;
    	while(right>=left) {
    		if(nums[med]==target) return med;
    		if(nums[left]<nums[med])
    		{
    			if(target<nums[med]&&target>=nums[left]) {
    				right=med-1;med=(right+left)/2;
    			} else {
    				left=med+1;med=(right+left)/2;
    			}
    		} else 
    		{
    			if(target>nums[med]&&target<=nums[left]) {
    				left=med+1;med=(right+left)/2;
    			} else {
    				// remove left
    				
    				right=med-1;med=(right+left)/2;
    			}
    		} 
    		
    	}
    	return -1;
    }
    public int findFirst(int[] nums, int target) {
    	int left=0; int right=nums.length-1;
    	int med=(left+right)/2;
    	int left_value=-1;
    	while(left<=right) {
        	if(nums[med]>target)
        		right=med-1;
        	else if(nums[med]<target)
        		left=med+1;
        	else {
        		left_value=med;
        		right=med-1;
        	}
        	med=(left+right)/2;
    	}

    	return left_value;
    }
    public int findLast(int[] nums, int target) {
    	int left=0; int right=nums.length-1;
    	int med=(left+right)/2;
    	int right_value=-1;
    	while(left<=right) {
        	if(nums[med]>target)
        		right=med-1;
        	else if(nums[med]<target)
        		left=med+1;
        	else {
        		right_value=med;
        		left=med+1;
        	}
        	med=(left+right)/2;
    	}

    	return right_value;
    }
    
    public int[] searchRange(int[] nums, int target) {
    	int []res=new int []{-1,-1};
    	int left_value=findFirst(nums,target);
    	if(left_value==-1) return res;
    	int right_value=findLast(nums,target);
    	if(right_value==-1) return res;
        return new int [] {left_value,right_value};
    }
    
    public int searchInsert(int[] nums, int target) {
    	int left=0; int right=nums.length-1;int med=(left+right)/2;
    	if(target>nums[right]) return right+1;
    	if(target<nums[left]) return 0;
    	while(left<=right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) right = mid-1;
            else left = mid + 1;
    	}
        return left;
    }
    
    public boolean isValidSudoku(char[][] board) {
    	
    	// check the line
    	for(int x=0;x<board.length;x++) {
    		Map<Character, Integer> map1 = new HashMap<Character,Integer>();
    		for(int y=0;y<board[0].length;y++) {
    			
    			if(Character.compare(board[x][y], '.')==0)
    				continue;
    			else if(board[x][y]>='0'&&board[x][y]<='9'){
    				if(!map1.containsKey(board[x][y]))
    					map1.put(board[x][y], 1);
    				else return false;
    			} else return false;
    		}
    	}
    	//check column
    	for(int y=0;y<board[0].length;y++) {
    		Map<Character, Integer> map1 = new HashMap<Character,Integer>();
    		for(int x=0;x<board.length;x++) {
    			
    			if(Character.compare(board[x][y], '.')==0)
    				continue;
    			else if(board[x][y]>='0'&&board[x][y]<='9') {
    				if(!map1.containsKey(board[x][y]))
    					map1.put(board[x][y], 1);
    				else return false;
    			} else return false;
    		}
    	}
    	
    	//check the box
    	for(int x=0;x<board.length;x=x+3) {
    		
    		for(int y=0;y<board[0].length;y=y+3) { // check every box
    			Map<Character, Integer> map1 = new HashMap<Character,Integer>();
    			for(int i=x;i<x+3;i++) {
    				for(int j=y;j<y+3;j++) {
    	    			if(Character.compare(board[i][j], '.')==0)
    	    				continue;
    	    			else if(board[i][j]>='0'&&board[i][j]<='9') {
    	    				if(!map1.containsKey(board[i][j]))
    	    					map1.put(board[i][j], 1);
    	    				else return false;
    	    			} else return false;
    					
    				}
    			}
    		}
    	}
    	
        return true;
    }
    
    public String countAndSay(int n) {
    	if(n==1) return "1"; 
    	String prev_str="1";
    	for(int i=2;i<=n;i++) {
    		//the loop for get res: i
    		int count=1;char prev_char=prev_str.charAt(0);
    		String cur_str=new String();
    		for(int m=1;m<prev_str.length();m++) {
    			
    			if(prev_str.charAt(m)==prev_char)
    			{
    				count++;
    			} else {
    				cur_str+=String.valueOf(count);
    				cur_str+=String.valueOf(prev_char);
    				count=1;
    				prev_char=prev_str.charAt(m);
    			}
    		}
			cur_str+=String.valueOf(count);
			cur_str+=String.valueOf(prev_char);
    		prev_str=cur_str;
    	}
        return prev_str;
    }
    
    HashSet<List<Integer>> result;
    int[] candidates;
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.parallelSort(candidates);
    	this.candidates=candidates;
    	this.result=new HashSet<List<Integer>>();
    	for(int i=0;i<candidates.length;i++) {
    		List<Integer> l1=new ArrayList<Integer>() ;
    		int cur_num=target;
    		
    		subFindone( i,  cur_num,l1);
    	}
        return new ArrayList<List<Integer>>(result);
    }
    public void subFindone(int num, int cur_num,List<Integer> l1) {
    	cur_num-=candidates[num];
    	if(cur_num<0) {
    		return;
    	}
    	l1.add(candidates[num]);
    	if(cur_num==0) {
    		List<Integer>l2=new ArrayList<Integer>(l1);
    		result.add(l2); 
    		l1.remove(l1.size()-1);
    		return;
    	}

    	for(int i=num+1;i<candidates.length;i++) {
//    		if(i>0&&candidates[i]==candidates[i-1])
//    			continue;
    		subFindone(i,cur_num,l1);
    	}
    	l1.remove(l1.size()-1);
    	
    }
    
    public List<List<Integer>> permute(int[] nums) {
    	HashSet<List<Integer>> res=new HashSet<List<Integer>>();
    	Queue<Integer> que=new LinkedList<Integer>();
    	for(int n:nums)
    	{
    		que.add(n);
    	}
    	List<Integer> curList=new ArrayList<Integer>();
    	iterate(res,que, curList);
    	List<List<Integer>> result=new ArrayList<List<Integer>>(res);
    	return result;
    }
    public void iterate(HashSet<List<Integer>> res,Queue<Integer> que,List<Integer> curList) {
    	if(que.isEmpty())
    	{
    		List<Integer>res_list=new ArrayList<Integer>(curList);
    		res.add(res_list);
    	}
    	else for(int i=0;i<que.size();i++) {
    		curList.add(que.poll());
    		iterate(res,que,curList);
    	}
    	if(curList.size()!=0)
    	que.add(curList.remove(curList.size()-1));
    }
    
    public List<List<String>> groupAnagrams(String[] strs) {
    	
    	Map<String,List<String> >map1=new HashMap<String,List<String> >();
    	for(String str:strs) {
    		char[] array=str.toCharArray();
    		Arrays.parallelSort(array);
    		String ord_str=String.valueOf(array);
    		if(map1.containsKey(ord_str)) {
    			List<String>cur_list=map1.get(ord_str);
    			cur_list.add(str);
    		} else {
    			List<String>cur_list=new ArrayList<String>();
    			cur_list.add(str);
    			map1.put(ord_str, cur_list);
    		}
    	}
    	List<List<String>> res=new ArrayList<List<String>>(map1.values());
    	return res;
    }
    
    public void serachRes(char[][] avail_board,int count) {
    	
    }
    
    public List<List<String>> solveNQueens(int n) {
    	List<List<String>>res=new ArrayList<List<String>>();
    	
    	char[][] board=new char[n][n] ;
    	for(int i=0;i<board.length;i++)
    		for(int j=0;j<board.length;j++) {
    			board[i][j]='.';
    		}
    	int count=n;
     	for(int i=0;i<board.length;i++)
    		for(int j=0;j<board.length;j++) {
    			char[][] new_board=board.clone();
    			//set the row 1
    			for(int y=0;y<board.length;y++) {
    				board[i][y]='1';
    			}
    			// set the col 1
    			for(int x=0;x<board.length;x++) {
    				board[x][j]='1';
    			}
    			// set the left diagnol 1
//    			for(int diag=0;)
//    			serachRes();
    		}
        return res;
    }

     public static void main(String []args){
//    	 ListNode l0=new ListNode(4);ListNode l1=new ListNode(3,l0);ListNode l2=new ListNode(2,l1);ListNode l3=new ListNode(1,l2);
//    	 ListNode l4=new ListNode(3);ListNode l5=new ListNode(2,l4);ListNode l6=new ListNode(1,l5);
//    	 ListNode[] l_temp= {l3, l6};[]
    	
    	 String nums[] ={"eat", "tea", "tan", "ate", "nat", "bat"};
    	 Main m1=new Main();
    	 System.out.print(m1.groupAnagrams(nums));
    	;
    	 
    	 return;
     }
}