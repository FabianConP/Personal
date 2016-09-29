#include<bits/stdc++.h>
typedef long long ll;
const int MAX = 15;

using namespace std;

int m[MAX][MAX];
ll dp[MAX][1 << MAX][2][2];

int n;

ll solve(int d, int u, int bad, int sp){
    if(d == n) {
        if(bad == 0 || (bad && sp)) return 1;
        return 0;
    }

    if(dp[d][u][bad][sp] != -1) return dp[d][u][bad][sp];

    ll best = 0;

    for(int i = 0; i < n; i++)
        if(((u >> i) & 1) == 0)
            if(m[d][i] == 0 || sp)
                best += solve(d + 1, u | (1 << i), 1, sp);
            else if(m[d][i] > 0)
                best += solve(d + 1, u | (1 << i), bad, max(sp, m[d][i] - 1));

    return (dp[d][u][bad][sp] = best);
}

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int ncases;

    cin >> ncases;

    for(int ncase = 0; ncase < ncases; ncase++){
        cin >> n;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                cin >> m[i][j];

        memset(dp, -1, sizeof(dp));

        ll ans = solve(0, 0, 0, 0);

        cout << "Case " << (ncase + 1) << ": " << ans << endl;

    }

    return 0;
}
