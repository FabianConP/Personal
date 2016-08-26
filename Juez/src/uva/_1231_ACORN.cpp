#include <bits/stdc++.h>
const int MAX_HEIGHT = 2010, MAX_TREES = 2010;

using namespace std;

int cnt[MAX_TREES][MAX_HEIGHT], dp[MAX_TREES][MAX_HEIGHT], tree[MAX_HEIGHT];

int main(){ //freopen("in.txt", "r", stdin);
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int ncases, t, h, f, height, acorns, acorn;
    while ( cin >> ncases) {
        if(ncases == 0) break;
        for(int ncase = 0; ncase < ncases; ncase++){
            cin >> t >> h >> f;
            for(int i = 0; i < t; i++)
                for(int j = 0; j <= h + 1; j++)
                    cnt[i][j] = dp[i][j] = 0;
            for(int i = 0; i < t; i++){
                cin >> acorns;
                for(int j = 0; j < acorns; j++){
                    cin >> acorn;
                    cnt[i][acorn]++;
                }
            }
            int ans = 0;
            for(int j = h; j >= 0; j--){
                tree[j] = 0;
                for(int i = 0; i < t; i++){
                    dp[i][j] = dp[i][j + 1];
                    if((j + f) <= h) dp[i][j] = max(dp[i][j], tree[j + f]);
                    dp[i][j] += cnt[i][j];
                    tree[j] = max(tree[j], dp[i][j]);
                    ans = max(ans, dp[i][j]);
                }
            }
            cout << ans << endl;
        }
    }
    return 0;
}
