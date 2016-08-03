public class NumArray {
    class SegmentTreeNode{
        int start, end, sum;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int sum){
            this.start=start;
            this.end=end;
            this.sum = sum;
        }
    }
    public void modify(SegmentTreeNode node, int ind, int val){
        if (node.start == ind && node.end == ind){
            node.sum=val;
            return;
        }
        int mid = node.start+(node.end-node.start)/2;
        if (mid>=ind) modify(node.left,ind,val);
        else modify(node.right,ind,val);
        node.sum=node.left.sum+node.right.sum;
    }
    public int query (SegmentTreeNode node, int start, int end){
        if (node.start == start && node.end == end) return node.sum;
        int mid = node.start+(node.end-node.start)/2;
        if (end<=mid) return query(node.left,start,end);
        else if (start<=mid && mid<=end) return query(node.left,start,mid)+query(node.right,mid+1,end);
        else return query(node.right,start,end);
    }
    public SegmentTreeNode build (int start, int end){
        if (start>end) return null;
        SegmentTreeNode node = new SegmentTreeNode(start,end,0);
        if (start<end){
            int mid = start+(end-start)/2;
            node.left=build(start,mid);
            node.right = build(mid+1,end);
        }
        return node;
    }
    private SegmentTreeNode root;
    public NumArray(int[] nums) {
        root = build(0,nums.length-1);
        for (int i=0; i<nums.length; i++){
            modify(root,i,nums[i]);
        }
    }

    void update(int i, int val) {
        modify(root,i,val);
    }

    public int sumRange(int i, int j) {
        return query(root,i,j);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);