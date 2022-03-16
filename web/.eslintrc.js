module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/typescript/recommended'
  ],
  parserOptions: {
    ecmaVersion: 2020
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'vue/no-unused-components': 'off', //不使用import的component
    '@typescript-eslint/no-unused-vars':0,//引用但未使用的变量
    'vue/no-unused-vars':0,//引用但未使用的变量
    '@typescript-eslint/no-explicit-any':0,//warning  Unexpected any. Specify a different type
    "@typescript-eslint/explicit-module-boundary-types": "off", // ts每个函数都要显式声明返回值
  }
}
