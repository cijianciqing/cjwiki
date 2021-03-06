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
        '@typescript-eslint/no-unused-vars': 0,//引用但未使用的变量
        'vue/no-unused-vars': 0,//引用但未使用的变量
        '@typescript-eslint/no-explicit-any': 0,//warning  Unexpected any. Specify a different type
        "@typescript-eslint/explicit-module-boundary-types": "off", // ts每个函数都要显式声明返回值
        "@typescript-eslint/ban-types": "off",//E:\java\cjProject\cjWiki\web\src\util\tool.ts: Don't use `object` as a type. The `object` type is currently hard to use
        "vue/no-deprecated-slot-attribute": "off",//`slot` attributes are deprecated  vue/no-deprecated-slot-attribute
        "@typescript-eslint/no-empty-function": "off",// Unexpected empty method 'onOk'
    }
}
