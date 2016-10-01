#include<bits/stdc++.h>

using namespace std;

const int _C = 6, _T = 7, _F4 = 8, _F5 = 9, _S = 10, _L = 11, _F = 12;
const int LIM = 1 << 13;

int r[13][5];

int dp[14][LIM][130];
int par[14][LIM];
int val[14][LIM];

int sumType(int ind, int type){
    int sum = 0;
    for(int i = 0; i < 5; i++)
        if(type == r[ind][i]) sum += type;
    return sum;
}

bool same(int ind, int s, int l){
    for(int i = 1; i < l; i++)
        if(r[ind][s + i] != r[ind][s]) return false;
    return true;
}

int chance(int ind){
    int sum = 0;
    for(int i = 0; i < 5; i++) sum += r[ind][i];
    return sum;
}

int three(int ind){
    return (same(ind, 0, 3) || same(ind, 1, 3) || same(ind, 2, 3)) ? chance(ind) : 0;
}

int four(int ind){
    return (same(ind, 0, 4) || same(ind, 1, 4)) ? chance(ind) : 0;
}

int five(int ind){
    return (same(ind, 0, 5)) ? 50 : 0;
}

bool straigt(int ind, int s, int l){
    for(int i = 1; i < l; i++)
        if(r[ind][s + i] - 1 != r[ind][s + i - 1]) return false;
    return true;
}

int s(int ind){
    return (straigt(ind, 0, 4) || straigt(ind, 1, 4) || straigt(ind, 2, 4)) ? 25 : 0;
}

int l(int ind){
    return (straigt(ind, 0, 5) || straigt(ind, 1, 5)) ? 35 : 0;
}

int f(int ind){
    return ((same(ind, 0, 3) && same(ind, 3, 2)) || (same(ind, 2, 3) && same(ind, 0, 2))) ? 40 : 0;
}

int solve(int index, int u, int acum){
    if(index < 0) return acum >= 63 ? 35 : 0;

    if(dp[index][u][acum] != -1) return dp[index][u][acum];

    int best = 0, cur = 0, aux;

    if(index <= 5)
        for(int i = 0; i < 13; i++){
            if((u >> i) & 1) continue;
            cur = sumType(i, index + 1);
            aux = solve(index - 1, u | (1 << i), acum + cur);
            if(aux + cur >= best){
                best = aux + cur;
                par[index][u] = i;
                val[index][u] = cur;
            }
        }
    else
        for(int i = 0; i < 13; i++){
            if((u >> i) & 1) continue;
            switch(index){
                case _C:
                    cur = chance(i);
                    break;
                case _T:
                    cur = three(i);
                    break;
                case _F4:
                    cur = four(i);
                    break;
                case _F5:
                    cur = five(i);
                    break;
                case _S:
                    cur = s(i);
                    break;
                case _L:
                    cur = l(i);
                    break;
                case _F:
                    cur = f(i);
                    break;
            }
            aux = solve(index - 1, u | (1 << i), acum);
            if(cur + aux >= best){
                best = cur + aux;
                par[index][u] = i;
                val[index][u] = cur;
            }
        }

    return (dp[index][u][acum] = best);
}

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int dice[5];
    int ans[13];

    while( cin >> dice[0] ){
        for(int i = 1; i < 5; i++) cin >> dice[i];
        sort(dice, dice + 5);
        for(int i = 0; i < 5; i++) r[0][i] = dice[i];
        for(int round = 1; round < 13; round++){
            for(int i = 0; i < 5; i++) cin >> dice[i];
            sort(dice, dice + 5);
            for(int i = 0; i < 5; i++) r[round][i] = dice[i];
        }

        memset(dp, -1, sizeof (dp));

        int best = solve(12, 0, 0);

        int run = 0, index = 12, cur = 0, sum = 0;

        while(index >= 0){
            ans[index] = val[index][run];
            run = run | (1 << par[index][run]);
            index--;
        }

        for(int i = 0; i < 13; i++){
            cout << ans[i] << " ";
            if(i <= 5) sum += ans[i];
        }

        if(sum >= 63) sum = 35;
        else sum = 0;
        cout << sum << " " << best << "\n";

    }

    return 0;
}
