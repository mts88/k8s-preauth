const express = require('express')
const jwt = require("jsonwebtoken");

const app = express()
const port = 3001

app.get('/me', (req, res) => {

  if(!req.headers.authorization) {
    return res.status(401).json({
      error: 'Missing authorization'
    })
  } else {
    if(req.headers.authorization !== 'Sooooocket') {
      return res.status(401).json({
        error: 'Invalid token'
      })
    }
  }

  console.log(process.env.TOKEN_SECRET)

  const token = jwt.sign( {
    username: 'mimicchio',
    authorities: [
      'hello',
      'world'
    ]
  }, process.env.TOKEN_SECRET);

  // K8s Ingress wants token in header response not in body
  res.header('Authorization', token).status(200).send({
    token
  })
})

app.get('/user', (req, res) => {
  res.json({
    foo: 'bar'
  })
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})
