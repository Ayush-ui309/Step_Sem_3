#include <iostream>
#include <vector>
using namespace std;
vector<int> warehouseSummary(vector<vector<int>>& grid){
    int total=0,maxVal=-1,row=0,col=0;
    for(int i=0;i<grid.size();i++){
        for(int j=0;j<grid[i].size();j++){
            total+=grid[i][j];
            if(grid[i][j]>maxVal){
                maxVal=grid[i][j];
                row=i;
                col=j;
            }
        }
    }
    return {total,row,col};
}
int main(){
    vector<vector<int>> grid={{4,9,2},{7,1,6},{3,12,5}};
    vector<int> result=warehouseSummary(grid);
    cout<<"Total = "<<result[0]<<endl;
    cout<<"MaxCoordinate = ("<<result[1]<<", "<<result[2]<<")";
    return 0;
}