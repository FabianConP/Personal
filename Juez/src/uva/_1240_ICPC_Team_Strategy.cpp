#include <bits/stdc++.h>
const int MAX_PROBLEMS = 12, MAX_TIME = 281;

using namespace std;

int dp[1 << MAX_PROBLEMS][MAX_TIME][4];

int ncases, problems, duration, ans;
vector< int > times[3];

int solve(int last, int spent, int s){
    if(__builtin_popcount(s) == problems) return 0;
    if(dp[s][spent][last] != -1) return dp[s][spent][last];
    int best = 0;
    for(int i = 0; i < 3; i++){
        if(i == last) continue;
        for(int j = 0; j < problems; j++){
            if(((s >> j) & 1) == 0 && (spent + times[i][j]) <= 280)
                best = max(best, 1 + solve(i, spent + times[i][j], (1 << j) | s));
        }
    }
    dp[s][spent][last] = best;
    ans = max(ans, best);
    return best;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> ncases;
    for(int ncase = 0; ncase < ncases; ncase++){
        cin >> problems;
        for(int i = 0; i < 3; i++){
            times[i].clear();
            for(int j = 0; j < problems; j++){
                cin >> duration;
                times[i].push_back(duration);
            }
        }
        memset(dp, -1, sizeof (dp));
        ans = 0;
        solve(3, 0, 0);
        cout << ans << endl;
    }
    return 0;
}
