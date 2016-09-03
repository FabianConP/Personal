#include<bits/stdc++.h>

const int MAX_PER = 100001, MAX_MEM = 110;

using namespace std;

bool dp[MAX_MEM][MAX_PER];

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, x;

    while(cin >> n >> x){
        if(n == 0 && x == 0) break;

        x--;

        vector< int > mem;
        int my = 0, item, pos = 0;
        double val = 0;

        for(int i = 0; i < n; i++){
            cin >> val;
            item = (int)(val * 1000);

            if(i == x) my = item;
            else mem.push_back(item);
        }

        sort(mem.begin(), mem.end());

        for(int i = 0; i < n; i++) for(int j = 0; j < MAX_PER; j++) dp[i][j] = false;

        dp[0][0] = true;

        int ans = MAX_PER;

        for(int i = 1; i < n; i++)
            for(int j = 0; j < MAX_PER; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - mem[i - 1] >= 0 && dp[i - 1][j - mem[i - 1]]) dp[i][j] |= dp[i - 1][j - mem[i - 1]];
                if(dp[i][j] && (j + my) > 50000) ans = min(ans, j);
            }

        if(my > 50000) cout << "100.00" << endl;
        else cout << fixed << setprecision(2) << (my*100.0)/(ans + my) << endl;
    }

    return 0;
}
