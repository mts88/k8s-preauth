const express = require('express')

const app = express()
const port = 3000

app.get('/', (req, res) => {
  res.json({
    cheers: 'Hello World upgraded version!',
    auth: req.headers.authorization,
    foo: "bar!!!"
    })
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})