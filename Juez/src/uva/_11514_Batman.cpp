#include<bits/stdc++.h>

const int MAX = 1010, INF = INT_MAX / 2;

using namespace std;

int p, v, e;

int sp[MAX][2], deff[MAX];
bool vill[MAX][MAX];
int dp[MAX][MAX];

int main(){

    //freopen("in.txt", "r", stdin);

    ios_base::sync_with_stdio(0);
    cin.tie(0);

    string name, mal, token;
    int val, calories, index;

    while(cin >> p >> v >> e){
        if(p == 0 && v == 0 && e == 0) break;

        map < string, int> ids;
        int id = 0;

        for(int i = 0; i < p; i++){
            cin >> name >> val >> calories;
            if(ids.find(name) == ids.end()) ids[name] = id++;
            index = ids[name];
            sp[index][0] = val;
            sp[index][1] = calories;
        }

        for(int i = 0; i <= v; i++){
            for(int j = 0; j <= p; j++){
                vill[i][j] = false;
                dp[i][j] = INF;
            }
        }

        for(int i = 0; i < v; i++){
            cin >> name >> val >> mal;
            deff[i] = val;
            istringstream ss(mal);
            while(getline(ss, token, ',')){
                if(ids.find(token) != ids.end()){
                    index = ids[token];
                    vill[i][index] = true;
                }
            }
        }

        for(int i = 0; i <= p; i++) dp[0][i] = 0;

        int best = 0;

        for(int i = 1; i <= v; i++){
            dp[i][0] = INF;
            for(int j = 1; j <= p; j++){
                dp[i][j] = INF;
                if(dp[i][j - 1] != INF)
                    dp[i][j] = dp[i][j - 1];
                if(vill[i - 1][j - 1] && sp[j - 1][0] >= deff[i - 1] && dp[i - 1][j - 1] != INF)
                    dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + sp[j - 1][1]);
            }
        }

        if(dp[v][p] <= e) cout << "Yes\n";
        else cout << "No\n";
    }

    return 0;
}
