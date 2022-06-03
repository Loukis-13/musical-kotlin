import http from 'k6/http';
import { check, group } from 'k6';

// export const options = {
//   vus: 10,
//   duration: '30s',
// };

const baseUrl = 'http://localhost:8084/v1';

const params = {
  headers: {
    'Content-Type': 'application/json',
  },
};

let id;

// 'log': r => console.log(JSON.stringify(r)) || true,

export default function () {
  group('Estilos', () => {
    group('GET /estilo', () => {
      check(http.get(baseUrl + '/estilo'), {
        'status was 200': (r) => r.status == 200,
        'response is an array': (r) => Array.isArray(r.json()),
      })
    })
    group('GET /estilo/:id', () => {
      check(http.get(baseUrl + '/estilo/1'), {
        'status was 200': r => r.status == 200,
        'response id': r => r.json('id') == 1,
        'response description': r => r.json('descricao') == 'Estilo 1',
      })
    })
    group('POST /estilo', () => {
      check(http.post(baseUrl + '/estilo', JSON.stringify({ descricao: "Estilo teste k6" }), params), {
        'status was 201': r => r.status == 201,
        'response id is int': r => Number.isInteger(r.json('id')),
        'response description': r => r.json('descricao') == 'Estilo teste k6',
        'set id': r => id = Number(r.json('id'))
      })
    })
    group('PUT /estilo', () => {
      check(http.put(baseUrl + '/estilo/' + id, JSON.stringify({ descricao: "Put estilo teste k6" }), params), {
        'status was 200': r => r.status == 200,
        'response id is int': r => Number.isInteger(r.json('id')),
        'response with same id': r => r.json('id') == id,
        'response description': r => r.json('descricao') == 'Put estilo teste k6',
      })
    })
    group('DELETE /estilo/:id', () => {
      check(http.del(baseUrl + '/estilo/' + id), {
        'status was 200': r => r.status == 200,
        'should return empty response': r => r.body == ""
      })
    })
  })

  group('Artistas', () => {
    group('GET /artista', () => {
      check(http.get(baseUrl + '/artista'), {
        'status was 200': (r) => r.status == 200,
        'response is an array': (r) => Array.isArray(r.json()),
      })
    })
    group('GET /artista/:id', () => {
      check(http.get(baseUrl + '/artista/1'), {
        'status was 200': r => r.status == 200,
        'response id': r => r.json('id') == 1,
        'response description': r => r.json('nome') == 'Artista 1',
      })
    })
    group('POST /artista', () => {
      check(http.post(baseUrl + '/artista', JSON.stringify({ nome: "Artista teste k6" }), params), {
        'status was 201': r => r.status == 201,
        'response id is int': r => Number.isInteger(r.json('id')),
        'response description': r => r.json('nome') == 'Artista teste k6',
        'set id': r => id = Number(r.json('id'))
      })
    })
    group('PUT /artista', () => {
      check(http.put(baseUrl + '/artista/' + id, JSON.stringify({ nome: "Put artista teste k6" }), params), {
        'status was 200': r => r.status == 200,
        'response id is int': r => Number.isInteger(r.json('id')),
        'response with same id': r => r.json('id') == id,
        'response description': r => r.json('nome') == 'Put artista teste k6',
      })
    })
    group('DELETE /artista/:id', () => {
      check(http.del(baseUrl + '/artista/' + id), {
        'status was 204': r => r.status == 204,
        'should return empty response': r => r.body == ""
      })
    })
  })

  group('Musicas', () => {
    group('GET /musica', () => {
      check(http.get(baseUrl + '/musica'), {
        'status was 200': (r) => r.status == 200,
        'response is an array': (r) => Array.isArray(r.json()),
      })
    })
    group('GET /musica/:id', () => {
      check(http.get(baseUrl + '/musica/1'), {
        'status was 200': r => r.status == 200,
        'response id': r => r.json('id') == 1,
        'response description': r => r.json('nome') == 'Musica 1',
      })
    })
    group('POST /musica', () => {
      check(http.post(baseUrl + '/musica', JSON.stringify({ nome: "Musica teste k6", artista: {id: 1}, estilo: {id: 1}  }), params), {
        'status was 200': r => r.status == 200,
        'response id is int': r => Number.isInteger(r.json('id')),
        'response description': r => r.json('nome') == 'Musica teste k6',
        'set id': r => id = Number(r.json('id'))
      })
    })
    group('PUT /musica', () => {
      check(http.put(baseUrl + '/musica/' + id, JSON.stringify({ nome: "Put musica teste k6", artista: {id: 2}, estilo: {id: 2} }), params), {
        'status was 200': r => r.status == 200,
        'response id is int': r => Number.isInteger(r.json('id')),
        'response with same id': r => r.json('id') == id,
        'response description': r => r.json('nome') == 'Put musica teste k6',
      })
    })
    group('DELETE /musica/:id', () => {
      check(http.del(baseUrl + '/musica/' + id), {
        'status was 204': r => r.status == 204,
        'should return empty response': r => r.body == ""
      })
    })
  })
}