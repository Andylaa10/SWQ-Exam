module.exports = {
    default: {
        require: ['src/features/**/*.ts'],
        requireModule: ['ts-node/register'],
        format: ['progress', 'json:reports/cucumber_report.json'],
        paths: ['src/features/**/*.feature'],
    }
};
