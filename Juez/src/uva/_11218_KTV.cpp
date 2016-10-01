#include<bits/stdc++.h>

using namespace std;

const int LIM = 1 << 9;

int dp[4][LIM];

int comb[90];
int ben[90];

int n;


int solve(int index, int used){
    if(index == 3) return 0;

    if(dp[index][used] != -1) return dp[index][used];

    int best = -1, cur;

    for(int i = 0; i < n; i++)
        if((used & comb[i]) == 0){
            cur = solve(index + 1, used | comb[i]);
            if(cur >= 0) best = max(best, cur + ben[i]);
        }

    return (dp[index][used] = best);
}

int mask(int a, int b, int c){
    return (1 << a) + (1 << b) + (1 << c);
}

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int a, b, c, ncase = 1;

    while(cin >> n){
        if(n == 0) break;
        for(int i = 0; i < n; i++){
            cin >> a >> b >> c >> ben[i];
            comb[i] = mask(a - 1, b - 1, c - 1);
        }

        memset(dp, -1, sizeof (dp));

        cout << "Case " << ncase++ << ": " << solve(0, 0) << endl;
    }

    return 0;
}
