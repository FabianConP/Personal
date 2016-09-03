#include<bits/stdc++.h>
#define ITEMS 16
#define MAX 1 << ITEMS
using namespace std;

int n, m, k, item;
int dp[MAX];

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0);

    while(cin >> n >> m){
        if(n == 0 && m == 0) break;

        vector< int > docs(n);

        for(int i = 0; i < n; i++){
            cin >> k;
            int mask = 0;
            for(int j = 0; j < k; j++){
                cin >> item;
                mask |= (1 << --item);
            }
            docs[i] = mask;
        }

        for(int i = 0; i < MAX; i++) dp[i] = MAX;

        dp[0] = 0;
        for(int i = 0; i < MAX; i++)
            for(int j = 0; j < n; j++)
                dp[i | docs[j]] = min(dp[i | docs[j]], dp[i] + 1);

        int ans = 0;
        for(int i = 0; i < m; i++){
            cin >> k;
            int mask = 0;
            for(int j = 0; j < k; j++){
                cin >> item;
                mask |= (1 << --item);
            }
            if(i != 0) cout << " ";
            ans = dp[mask];
            if(ans > n) ans = 0;
            cout << ans;
        }
        cout << endl;
    }

    return 0;
}

