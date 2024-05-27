const express = require('express');
const firebase = require('firebase/compat/app');
require('firebase/compat/auth');
const bodyParser = require('body-parser');
const path = require('path');
const passport = require('passport');
const GoogleStrategy = require('passport-google-oauth20').Strategy;
const session = require('express-session');

// Konfigurasi Firebase Anda
const firebaseConfig = {
    apiKey: "AIzaSyAD14tNBOOKsKpGktvqg4SsA_sD2DHRY1k",
    authDomain: "abe-project-424005.firebaseapp.com",
    projectId: "abe-project-424005",
    storageBucket: "abe-project-424005.appspot.com",
    messagingSenderId: "629983499554",
    appId: "1:629983499554:web:5d7822573cbfd3d158817b",
    measurementId: "G-77DY8DFFW4"
};

// Inisialisasi Firebase
if (!firebase.apps.length) {
  firebase.initializeApp(firebaseConfig);
}

const app = express();
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// Middleware untuk melayani file statis
app.use(express.static(path.join(__dirname, 'public')));

app.use(session({
  secret: 'aku-anak-nakal-rwarrr-12345',
  resave: false,
  saveUninitialized: false
}));

app.use(passport.initialize());
app.use(passport.session());

passport.serializeUser(function(user, done) {
  done(null, user);
});

passport.deserializeUser(function(user, done) {
  done(null, user);
});

passport.use(new GoogleStrategy({
    clientID: process.env.clientID,
    clientSecret: process.env.clientSecret,
    callbackURL: 'http://localhost:3000/auth/google/callback'
  },
  function(accessToken, refreshToken, profile, cb) {
    return cb(null, profile);
  }
));

app.get('/auth/google',
  passport.authenticate('google', { scope: ['profile'] }));

app.get('/auth/google/callback', 
  passport.authenticate('google', { failureRedirect: '/login' }),
  function(req, res) {
    res.redirect('/dashboard');
  });

app.get('/dashboard', (req, res) => {
  if (!req.user) {
    res.redirect('/auth/google');
  } else {
    res.send('Selamat datang, ' + req.user.displayName);
  }
});

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.post('/login', (req, res) => {
  const email = req.body.email;
  const password = req.body.password;

  firebase.auth().signInWithEmailAndPassword(email, password)
    .then((userCredential) => {
      var user = userCredential.user;
      res.send('Berhasil masuk sebagai ' + user.email);
    })
    .catch((error) => {
      res.send('Gagal masuk: ' + error.message);
    });
});

app.post('/signup', (req, res) => {
  const email = req.body.email;
  const password = req.body.password;

  firebase.auth().createUserWithEmailAndPassword(email, password)
    .then((userCredential) => {
      var user = userCredential.user;
      res.send('Berhasil mendaftar dengan email ' + user.email);
    })
    .catch((error) => {
      res.send('Gagal mendaftar: ' + error.message);
    });
});

app.listen(3000, () => {
  console.log('Aplikasi web berjalan di http://localhost:3000');
});

