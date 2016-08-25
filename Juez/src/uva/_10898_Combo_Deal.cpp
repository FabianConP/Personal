#include<bits/stdc++.h>
#define ll long long

const int MAX_DISHES = 10;
const ll INF = 1ll << 60;
using namespace std;

int dishes, combos;
vector < int > di;
vector < vector < int > > com;

ll dp[MAX_DISHES][MAX_DISHES][MAX_DISHES][MAX_DISHES][MAX_DISHES][MAX_DISHES];

ll solve(int a, int b, int c, int d, int e, int f){
    if(dp[a][b][c][d][e][f] >= 0L) return dp[a][b][c][d][e][f];
    vector< int > combo;
    ll best = INF;
    int aa, bb, cc, dd, ee, ff;
    for(int i = 0; i < combos; i++){
        combo = com[i];
        aa = (dishes > 0) ? a - combo[0] : a;
        bb = (dishes > 1) ? b - combo[1] : b;
        cc = (dishes > 2) ? c - combo[2] : c;
        dd = (dishes > 3) ? d - combo[3] : d;
        ee = (dishes > 4) ? e - combo[4] : e;
        ff = (dishes > 5) ? f - combo[5] : f;
        if(aa >= 0 && bb >= 0 && cc >= 0 && dd >= 0 && ee >= 0 && ff >= 0)
            best = min(best, combo[dishes] + solve(aa, bb, cc, dd, ee, ff));
    }
    if(a > 0) best = min(best, di[0] + solve(a - 1, b, c, d, e, f));
    if(b > 0) best = min(best, di[1] + solve(a, b - 1, c, d, e, f));
    if(c > 0) best = min(best, di[2] + solve(a, b, c - 1, d, e, f));
    if(d > 0) best = min(best, di[3] + solve(a, b, c, d - 1, e, f));
    if(e > 0) best = min(best, di[4] + solve(a, b, c, d, e - 1, f));
    if(f > 0) best = min(best, di[5] + solve(a, b, c, d, e, f - 1));
    dp[a][b][c][d][e][f] = best;
    return best;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int price, orders;
    while(cin >> dishes){
        di.clear();
        for(int i = 0; i < dishes; i++){
            cin >> price;
            di.push_back(price);
        }
        com.clear();
        cin >> combos;
        for(int j = 0; j < combos; j++){
            vector< int > combo;
            for(int k = 0; k <= dishes; k++){
                cin >> price;
                combo.push_back(price);
            }
            com.push_back(combo);
        }
        memset(dp, -1, sizeof(dp));
        dp[0][0][0][0][0][0] = 0;
        cin >> orders;
        int aa, bb, cc, dd, ee, ff;
        for(int i = 0; i < orders; i++){
            aa = bb = cc = dd = ee = ff = 0;
            if(dishes > 0) cin >> aa;
            if(dishes > 1) cin >> bb;
            if(dishes > 2) cin >> cc;
            if(dishes > 3) cin >> dd;
            if(dishes > 4) cin >> ee;
            if(dishes > 5) cin >> ff;
            cout << solve(aa, bb, cc, dd, ee, ff) << endl;
        }
    }
    return 0;
}

