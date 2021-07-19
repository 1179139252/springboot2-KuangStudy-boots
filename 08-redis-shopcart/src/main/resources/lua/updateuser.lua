-- 成功返回1、没有设置返回0
-- 如果redis没找到。就直接添加
if redis.call('get',KEYS[1]) == nil then
    redis.call('set',KEYS[1],ARGV[1]);
    return 1
end
-- 如果旧值等于新值，不进行操作，如果不相同就执行更新
if redis.call('get',KEYS[1]) == ARGV[1] then
    return 0
else
    redis.call('set',KEYS[1],ARGV[1]);
    return 1
end