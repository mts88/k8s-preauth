const express = require('express')

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

  // K8s vuole il token in un header di risposta non nel body
  res.header('Authorization', 'HelloooooooToken' ).status(204).send()
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})