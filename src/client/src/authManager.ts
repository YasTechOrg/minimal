export function getAuth(): string
{
    return `Bearer ${localStorage.getItem("at")}`
}
export function checkAuth(): boolean
{
    return localStorage.getItem("at") != null
}
export function setAuth(token: string)
{
    localStorage.setItem("at", token)
    localStorage.setItem("at_time", String(new Date()))
}
export function removeAuth()
{
    localStorage.removeItem("at")
    localStorage.removeItem("userData")
}