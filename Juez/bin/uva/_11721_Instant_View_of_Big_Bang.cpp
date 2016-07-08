#include <bits/stdc++.h>
#define MAX 1000
#define INF 10000000
using namespace std;

vector<pair<int, int> > G[MAX +1];
int dist[MAX +1];
bool vis[MAX+1];
bool cycle[MAX+1];

void clean(int n){
    for(int i = 0; i<= n; i++){
        G[i] = vector< pair<int, int> >();
        vis[i] = false;
        dist[i] = INF;
        cycle[i] = false;
    }
}

void bellmanFord(int s, int n){
    dist[s] = 0;
    vector<pair<int, int> >::iterator it;
    for(int i = 0; i<= n-1; i++){
        for(int u = 0; u<=n; u++){
            for(it = G[u].begin(); it != G[u].end(); ++it){
                int v = it->first, w = it->second;
                if(dist[u]!=INF && dist[u]+w<dist[v])
                    dist[v] = dist[u] + w;
            }
        }
    }
    for(int j = 0; j<=n; j++){
        for(it = G[j].begin(); it != G[j].end(); ++it){
            int t = it->first, w = it->second;
            if(dist[j]!=INF && dist[j]+w<dist[t]){
                dist[t] = dist[j] +w;
                cycle[t] = true;
            }
        }
    }
}

void dfs(int j){
    vector<pair<int, int> >::iterator it;
    for(it = G[j].begin(); it != G[j].end(); ++it){
        int node = it->first;
        if(!cycle[node]){
            cycle[node] = true;
            dfs(node);
        }
    }
}

int main(){
    int t,n,m,u,v,w,q;
    scanf("%d",&t);
    for(int tt = 0; tt<t; tt++){
        scanf("%d %d", &n, &m);
        clean(n+1);
        for(int i = 0; i<n; i++)
            G[0].push_back(make_pair(i+1,0));
        for(int i = 0; i<m; i++){
            scanf("%d %d %d", &u, &v, &w);
            G[v+1].push_back(make_pair(u+1, w));
        }
        printf("Case %d:",tt+1);
        bellmanFord(0, n);
        for(int i = 1; i<=n; i++)
            if(cycle[i])
                dfs(i);
        int cycleCount = 0;
        for(int i = 1; i<=n; i++)
            if(cycle[i]){
                printf(" %d", i-1);
                cycleCount++;
            }
        if(cycleCount==0)
            printf(" impossible");
        printf("\n");
    }
    return 0;
}


