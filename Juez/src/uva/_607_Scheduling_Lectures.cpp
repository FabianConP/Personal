#include <bits/stdc++.h>
#define ll long long
const int MAX = 1010;
ll INF = 10000000000;

using namespace std;

ll sum[MAX], lec[MAX], dis[MAX];

int topics, l, c;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int ncase = 1;
    while(cin >> topics){
        if(topics == 0) break;
        cin >> l >> c;
        cin >> sum[1];
        sum[0] = lec[0] = dis[0] = 0;
        for(int i = 2; i <= topics; i++){
            cin >> sum[i];
            sum[i] += sum[i - 1];
        }
        ll cost = 0;
        for(int i = 1; i <= topics; i++){
            lec[i] = dis[i] = INF;
            for(int j = i; (sum[i] - sum[j - 1]) <= l && j >= 1; j--){
                cost = l - (sum[i] - sum[j - 1]);
                cost = (cost == 0l) ? 0: cost <= 10 ? -c: (cost - 10) * (cost - 10);
                if(lec[i] > (1 + lec[j - 1]) || (lec[i] == (1 + lec[j - 1]) && dis[i] > (cost + dis[j - 1]))){
                    lec[i] = lec[j - 1] + 1;
                    dis[i] = cost + dis[j - 1];
                }
            }
        }
        if(ncase != 1) cout << endl;
        cout << "Case "<< ncase++ << ":" << endl;
        cout << "Minimum number of lectures: " << lec[topics] << endl;
        cout << "Total dissatisfaction index: " << dis[topics] << endl;
    }
    return 0;
}

