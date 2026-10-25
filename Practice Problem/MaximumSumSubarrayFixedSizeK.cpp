#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int maxSumSubarray(vector<int>& sales,int k){
    int sum=0;
    for(int i=0;i<k;i++)sum+=sales[i];
    int maxSum=sum;
    for(int i=k;i<sales.size();i++){
        sum+=sales[i]-sales[i-k];
        maxSum=max(maxSum,sum);
    }
    return maxSum;
}
int main(){
    vector<int> sales={2,1,5,1,3,2};
    int k=3;
    cout<<maxSumSubarray(sales,k);
    return 0;
}