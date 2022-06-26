import format
import pandas as pd

df = pd.read_csv('notas_alunos.csv')

df['media'] = (df['nota_1'] + df['nota_2']) / 2

df.loc[df['faltas'] <= 5, 'situacao'] = 'APROVADO'
df.loc[df['faltas'] > 5, 'situacao'] = 'REPROVADO'

df.loc[df['media'] < 7, 'situacao'] = 'REPROVADO'

df.to_csv('alunos_situacao.csv', index=False)

format.msg1(50, 'RELATÓTIO DE SITUAÇÃO',
            f"Maior número de fatas: {df['faltas'].max()}\n"
            f"Media da turma: {df['media'].median()}\n"
            f"Maior média: {df['media'].max()}", bg_color='')