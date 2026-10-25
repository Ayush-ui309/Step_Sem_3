#include <iostream>
#include <vector>
using namespace std;
vector<int> pairSumSorted(vector<int>& nums,int target){
    int left=0,right=nums.size()-1;
    while(left<right){
        int sum=nums[left]+nums[right];
        if(sum==target)return {nums[left],nums[right]};
        else if(sum<target)left++;
        else right--;
    }
    return {};
}
int main(){
    vector<int> nums={-4,-1,0,3,5,9};
    int target=4;
    vector<int> result=pairSumSorted(nums,target);
    if(result.empty())cout<<"Not Found";
    else cout<<"("<<result[0]<<", "<<result[1]<<")";
    return 0;
}