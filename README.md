
## MS clientes

## Usagem/Exemplos

#### Retorna um cliente

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | **Obrigatório**. |

Method: GET
```
  http://host.docker.internal:59434/clientes?cpf=17849
```
Body (raw)
```json
{
  "id": 1,
  "cpf": "xxx.xxx.xxx-xx",
  "nome": "Pedro",
  "idade": 18
}
```

#### Criar um cliente

Method: POST
```
  http://host.docker.internal:59434/clientes
```
Body (pretty)
```json
{
  "id": 1,
  "cpf": "xxx.xxx.xxx-xx",
  "nome": "Pedro",
  "idade": 18
}
```


#### Atualizar um cliente

Method: PUT
```
  http://host.docker.internal:59434/clientes
```
Body (pretty)
```json
{
  "id": 1,
  "cpf": "xxx.xxx.xxx-xx",
  "nome": "Pedro",
  "idade": 18
}
```


#### Delatar um cliente

Method: DELETE
```
  http://host.docker.internal:59434/clientes/1
```
