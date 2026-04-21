def appliquer_welsh_powell(graphe):
    ordre = sorted(graphe.keys(), key=lambda x: len(graphe[x]), reverse=True)
    
    coloriage = {}
    num_couleur = 1
    sommets_restants = ordre.copy()

    while sommets_restants:
        equipe_actuelle = []
        deja_traites = []
        
        premier = sommets_restants[0]
        coloriage[premier] = num_couleur
        equipe_actuelle.append(premier)
        deja_traites.append(premier)
        
        for i in range(1, len(sommets_restants)):
            sommet = sommets_restants[i]
            if all(sommet not in graphe[membre] for membre in equipe_actuelle):
                coloriage[sommet] = num_couleur
                equipe_actuelle.append(sommet)
                deja_traites.append(sommet)
        
        for element in deja_traites:
            sommets_restants.remove(element)
            
        num_couleur += 1
        
    return coloriage

reseau = {
    'A': ['B', 'E', 'F', 'G'],
    'B': ['A', 'C', 'D', 'H'],
    'C': ['B', 'D'],
    'D': ['B', 'C', 'E', 'H'],
    'E': ['A', 'D', 'F', 'G'],
    'F': ['A', 'E'],
    'G': ['A', 'E', 'H'],
    'H': ['B', 'D', 'G']
}

coloriage_final = appliquer_welsh_powell(reseau)

print(coloriage_final)