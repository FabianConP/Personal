#include<bits/stdc++.h>

using namespace std;

#define REP(i, n) for (int i = 0; i < (int)(n); ++i)

const int MAXN = 1010;
string S;
int N, gap;
int sa[MAXN], ra[MAXN], tmp[MAXN], lcp[MAXN];

bool sufCmp(int i, int j){
    if (ra[i] != ra[j])
        return ra[i] < ra[j];
    i += gap;
    j += gap;
    return (i < N && j < N) ? ra[i] < ra[j] : i > j;
}

void buildSA(){
    N = S.size();
    REP(i, N) sa[i] = i, ra[i] = S[i];
    for (gap = 1;; gap *= 2){
        sort(sa, sa + N, sufCmp);
        REP(i, N - 1) tmp[i + 1] = tmp[i] + sufCmp(sa[i], sa[i + 1]);
        REP(i, N) ra[sa[i]] = tmp[i];
        if (tmp[N - 1] == N - 1) break;
    }
}

void buildLCP(){
    for (int i = 0, k = 0; i < N; ++i) if (ra[i] != N - 1){
        for (int j = sa[ra[i] + 1]; S[i + k] == S[j + k];)
        ++k;
        lcp[ra[i]] = k;
        if (k)--k;
    }
}

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    string val;

    while(getline(cin, S)){

        REP(i, S.size()) S[i] = tolower(S[i]);

        buildSA();
        buildLCP();

        int queries, a;

        getline(cin, val);
        queries = atoi(val.c_str());

        REP(q, queries){
            getline(cin, val);
            a = atoi(val.c_str());

            int times = 0, bestTimes = 0, start = -1, bestStart;

            if(N - sa[0] >= a){
                if(start == -1) start = sa[0];
                times++;
                if(times > bestTimes){
                    bestTimes = times;
                    bestStart = start;
                }
            }

            REP(i, N - 1){
                if(N - sa[i + 1] >= a)
                    if(lcp[i] >= a){
                        if(start == -1) start = sa[i + 1];
                        times++;
                        if(times > bestTimes){
                            bestTimes = times;
                            bestStart = start;
                        }
                    }else{
                        if(bestTimes == 0){
                            bestTimes = 1;
                            bestStart = sa[i + 1];
                        }
                        start = -1;
                        times = 1;
                    }
            }
            cout << max(1, bestTimes) << " ";
            REP(i, a)
                cout << S[i + bestStart];
            cout << endl;
        }
    }

    return 0;
}


