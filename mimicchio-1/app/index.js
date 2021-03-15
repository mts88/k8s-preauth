const express = require('express')
const jwt = require("jsonwebtoken");

const app = express()
const port = 3000

app.get('/', (req, res) => {

  try {
    const decoded = jwt.verify(req.headers.authorization, process.env.TOKEN_SECRET, {complete: true})

    res.json({
      payload: decoded.payload,
      header: decoded.header
      })

  } catch(err) {
    console.log(err)
    return res.status(401).json({
      error: 'Unverified token'
    })
  }

})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})